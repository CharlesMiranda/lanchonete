package health.maida.lanchonete.controller;

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

import health.maida.lanchonete.entity.Produto;
import health.maida.lanchonete.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	
	@Autowired
    private ProdutoRepository _produtoRepository;

    @RequestMapping(value = "/produto", method = RequestMethod.GET)
    public List<Produto> Get() {
        return _produtoRepository.findAll();
    }

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> GetById(@PathVariable(value = "id") long id, 
    		@AuthenticationPrincipal UserDetails userDetails)
    {
        Optional<Produto> produto = _produtoRepository.findById(id);
        if(produto.isPresent())
            return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PreAuthorize("hasRole('GESTOR')")
    @RequestMapping(value = "/produto", method =  RequestMethod.POST)
    public ResponseEntity<Produto> Post(@Valid @RequestBody Produto produto, @AuthenticationPrincipal UserDetails userDetails)
    {
    	//verificando se já existe descricao cadastrada
		if(_produtoRepository.pesquisarDescricao(produto.getDescricao()).isPresent()) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}

		_produtoRepository.save(produto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	        
    }

    @PreAuthorize("hasRole('GESTOR')")
    @RequestMapping(value = "/produto/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Produto> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Produto newProduto, @AuthenticationPrincipal UserDetails userDetails)
    {
    	//verificando se já existe descricao cadastrada para outro produto
		if(_produtoRepository.pesquisarDescricaoDiferenteId(newProduto.getDescricao(), id).isPresent()) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
    	
        Optional<Produto> oldProduto = _produtoRepository.findById(id);
        if(oldProduto.isPresent()){
        	
    		Produto produto = oldProduto.get();
        	
    		produto.setDescricao(newProduto.getDescricao());
            produto.setPreco(newProduto.getPreco());
            produto.setCategoria(newProduto.getCategoria());
        	produto.setHabilitadoCestaVenda(newProduto.getHabilitadoCestaVenda());
		
        	_produtoRepository.save(produto);
    	
            return new ResponseEntity<Produto>(produto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('GESTOR')")
    @RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id, @AuthenticationPrincipal UserDetails userDetails)
    {
        Optional<Produto> produto = _produtoRepository.findById(id);
        if(produto.isPresent()){
        	_produtoRepository.delete(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
