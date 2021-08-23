package health.maida.lanchonete.config;

public class SecurityConstants {
	
	// EXPIRATION_TIME = 10 minutos
	public static final long EXPIRATION_TIME = 600000L;
	public static final String SECRET = "healthMaida";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static final String[] SIGN_UP_URL_GET = { 
			"/produto"
	};
	
	public static final String[] SIGN_UP_URL_POST = { 
			"/login", "/gestor", "/cliente"
	};
	
	public static final String[] SIGN_USER_URL = { 
			""
	};
	
	public static final String[] SIGN_ADMIN_URL = { 
			"/cliente", "/produto"
	};

}
