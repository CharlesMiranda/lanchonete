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

import health.maida.lanchonete.entity.Gestor;
import health.maida.lanchonete.repository.GestorRepository;
import health.maida.lanchonete.utils.Seguranca;

@RestController
public class GestorController {
	
	@Autowired
    private GestorRepository _gestorRepository;

    @RequestMapping(value = "/gestor", method = RequestMethod.GET)
    public List<Gestor> Get() {
        return _gestorRepository.findAll();
    }

    @RequestMapping(value = "/gestor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Gestor> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Gestor> gestor = _gestorRepository.findById(id);
        if(gestor.isPresent())
            return new ResponseEntity<Gestor>(gestor.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/gestor", method =  RequestMethod.POST)
    public ResponseEntity<Gestor> Post(@Valid @RequestBody Gestor gestor)
    {
    	
    	try {
    		
    		//verificando se já existe cadastrado
    		if(_gestorRepository.findAll().size() > 0) {
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
    		}

			gestor.setSenha(Seguranca.criptografarSenha(gestor.getSenha()));
	
			_gestorRepository.save(gestor);
			return new ResponseEntity<>(gestor, HttpStatus.OK);
			
    	} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	        
    }

    @RequestMapping(value = "/gestor/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Gestor> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Gestor newGestor)
    {
        Optional<Gestor> oldGestor = _gestorRepository.findById(id);
        if(oldGestor.isPresent()){
        	
    		Gestor gestor = oldGestor.get();
        	
        	try {
            	gestor.setNomeEstabelecimento(newGestor.getNomeEstabelecimento());
            	gestor.setEmail(newGestor.getEmail());
				gestor.setSenha(Seguranca.criptografarSenha(newGestor.getSenha()));
			
	        	_gestorRepository.save(gestor);
        	} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
            
            return new ResponseEntity<Gestor>(gestor, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/gestor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Gestor> gestor = _gestorRepository.findById(id);
        if(gestor.isPresent()){
        	_gestorRepository.delete(gestor.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
