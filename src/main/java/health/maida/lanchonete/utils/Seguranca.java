package health.maida.lanchonete.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguranca {
	
	public static String criptografarSenha(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
        
        return String.valueOf(messageDigest);
	}

}
