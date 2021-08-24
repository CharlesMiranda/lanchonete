package health.maida.lanchonete.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import health.maida.lanchonete.entity.Cliente;
import health.maida.lanchonete.entity.ItemPedido;
import health.maida.lanchonete.entity.Pedido;
import health.maida.lanchonete.entity.Produto;
import health.maida.lanchonete.repository.ClienteRepository;
import health.maida.lanchonete.repository.PedidoRepository;
import health.maida.lanchonete.exceptions.ResourceBadRequestException;
import health.maida.lanchonete.exceptions.ResourceNotFoundException;

@RestController
public class PedidoController {
	

	@Autowired
    private ClienteRepository _clienteRepository;
	
	@Autowired
    private PedidoRepository _pedidoRepository;

	@PreAuthorize("hasAnyRole('GESTOR')")
    @RequestMapping(value = "/pedido", method = RequestMethod.GET)
    public List<Pedido> Get() {
        return _pedidoRepository.findAll();
    }

	@PreAuthorize("hasAnyRole('GESTOR','CLIENTE')")
    @RequestMapping(value = "/pedido/{clienteEmail}/consultar_pedido", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetById(@PathVariable(value = "clienteEmail") String clienteEmail, 
    		@AuthenticationPrincipal UserDetails userDetails)
    {
        Optional<List<Pedido>> pedidoList = _pedidoRepository.pesquisarPorCliente(clienteEmail);
        if(pedidoList.isPresent())
            return new ResponseEntity<List<Pedido>>(pedidoList.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PreAuthorize("hasAnyRole('GESTOR','CLIENTE')")
    @RequestMapping(value = "/pedido/{clienteEmail}/solicitar_pedido", method =  RequestMethod.POST)
    public ResponseEntity<Pedido> Post(@PathVariable(value = "clienteEmail") String clienteEmail, @Valid @RequestBody Pedido pedido, @AuthenticationPrincipal UserDetails userDetails)
    {
    	Calendar data = Calendar.getInstance();
    	Date dataAtual = data.getTime();
    	
    	return _clienteRepository.pesquisarPorEmail(clienteEmail).map(cliente -> {
    		
    		Double valorTotal = 0D;
    		
    		pedido.setCliente(cliente);
    		
    		pedido.setSituacaoPagamentoAberto();
    		
    		pedido.setSituacaoPedidoAguardando();
    		
    		pedido.setCreatedAt(dataAtual);
    		
    		for (ItemPedido itemPedido : pedido.getItensPedido()) {
    			
    			Produto produtoItem = itemPedido.getProduto();
    			
    			itemPedido.setTotalItem(produtoItem.getPreco() * itemPedido.getQuantidade());
        		
    			valorTotal += itemPedido.getTotalItem();
    			
    			itemPedido.setCreatedAt(dataAtual);
    			
    			itemPedido.setPedido(pedido);
        		
			}
        	
        	pedido.setValorTotal(valorTotal);
        	    		            
            _pedidoRepository.save(pedido);
            
    		return new ResponseEntity<>(pedido, HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente de e-mail " + clienteEmail + " não encontrado."));
        
    }
    
    private Pedido validarPedidoCliente(String clienteEmail, long pedidoId) {
    	
    	Optional<Cliente> cliente = _clienteRepository.pesquisarPorEmail(clienteEmail);
    	if(!cliente.isPresent()) {
            throw new ResourceNotFoundException("cliente de e-mail " + clienteEmail + " não encontrado.");
        }
    	    	
    	Optional<Pedido> verificacaoPedido = _pedidoRepository.findById(pedidoId);
    	if(!verificacaoPedido.isPresent()) {
            throw new ResourceNotFoundException("Pedido " + pedidoId + " não encontrado.");
        }
    	
    	return verificacaoPedido.get();
    	
    }

    @PreAuthorize("hasRole('GESTOR')")
    @RequestMapping(value = "/pedido/{clienteEmail}/gerenciar_pedido/{pedidoId}", method =  RequestMethod.PUT)
    public ResponseEntity<Pedido> Put(@PathVariable(value = "clienteEmail") String clienteEmail,
    								  @PathVariable(value = "pedidoId") long pedidoId,
    								  @Valid @RequestBody Pedido newPedido, @AuthenticationPrincipal UserDetails userDetails)
    {
    	
    	Calendar data = Calendar.getInstance();
    	Date dataAtual = data.getTime();
    	
    	Pedido pedido  = validarPedidoCliente(clienteEmail, pedidoId);

    	pedido.setModifiedAt(dataAtual);
    	pedido.setSituacaoPedido(newPedido.getSituacaoPedido());
    	
    	_pedidoRepository.save(pedido);
    	
    	return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('GESTOR','CLIENTE')")
    @RequestMapping(value = "/pedido/{clienteEmail}/cancelar_pedido/{pedidoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "clienteEmail") String clienteEmail,
									     @PathVariable(value = "pedidoId") long pedidoId,
									     @AuthenticationPrincipal UserDetails userDetails)
    {

    	Calendar data = Calendar.getInstance();
    	
    	Pedido pedido  = validarPedidoCliente(clienteEmail, pedidoId);
    	
    	//verificando se pode cancelar o pedido
    	if(!pedido.getSituacaoPedido().getDescricao().toLowerCase().contains("aguardando")) {
            
    		throw new ResourceBadRequestException("Pedido não pode mais ser cancelado.");
        }
    	
    	if(!pedido.getCliente().getEmail().toLowerCase().contains(clienteEmail)) {
    		
    		throw new ResourceBadRequestException("Pedido de código " + pedidoId + " e cliente " + clienteEmail+" não encontrado");
        }
    	
    	pedido.setModifiedAt(data.getTime());	

    	pedido.setSituacaoPedidoCancelado();
        
        _pedidoRepository.save(pedido);
                
        return new ResponseEntity<>(HttpStatus.OK);
    }
    	
    

}
