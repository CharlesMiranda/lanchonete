package health.maida.lanchonete.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import health.maida.lanchonete.entity.Cliente;
import health.maida.lanchonete.repository.ClienteRepository;
import health.maida.lanchonete.utils.Seguranca;



@RestController
public class ClienteController {
	
	@Autowired
    private ClienteRepository _clienteRepository;

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public List<Cliente> Get() {
        return _clienteRepository.findAll();
    }

    @RequestMapping(value = "/cliente/{email}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> GetById(@PathVariable(value = "email") String email)
    {
    	
        Optional<Cliente> cliente = _clienteRepository.pesquisarPorEmail(email);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
    public ResponseEntity<Cliente> Post(@Valid @RequestBody Cliente cliente)
    {
    	
    	try {
		    		
    		cliente.setSenha(Seguranca.criptografarSenha(cliente.getSenha()));
			_clienteRepository.save(cliente);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
			
    	} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

    	
    	        
    }

    @RequestMapping(value = "/cliente/{email}", method =  RequestMethod.PUT)
    public ResponseEntity<Cliente> Put(@PathVariable(value = "email") String email, @Valid @RequestBody Cliente newCliente)
    {
        Optional<Cliente> oldCliente = _clienteRepository.pesquisarPorEmail(email);
        if(oldCliente.isPresent()){
        	
        	Cliente cliente = oldCliente.get();
        	
        	try {
        		
	        	cliente.setNome(newCliente.getNome());
	    		cliente.setDataNascimento(newCliente.getDataNascimento());
	    		cliente.setTelefone(newCliente.getTelefone());
	    		cliente.setSenha(Seguranca.criptografarSenha(newCliente.getSenha()));
		
				_clienteRepository.save(cliente);
			
        	} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
        	
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        	
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cliente/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "email") String email)
    {
        Optional<Cliente> cliente = _clienteRepository.pesquisarPorEmail(email);
        if(cliente.isPresent()){
        	_clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
