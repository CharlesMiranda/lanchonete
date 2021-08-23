package health.maida.lanchonete.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Seguranca {
	
	public static PasswordEncoder getCodificadorSenha() {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}
	
	public static String criptografarSenha(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		PasswordEncoder passwordEncoder = getCodificadorSenha();
		return passwordEncoder.encode(senha);
	}

}
