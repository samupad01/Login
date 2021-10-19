package dad.login.auth;

import org.apache.directory.api.ldap.model.exception.LdapAuthenticationException;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

/**
 * Autenticación de usuarios usando un servidor LDAP. El servidor utilizado es
 * JumpCloud, donde previamente he dado de alta los usuarios.
 * 
 * @author fvarrui
 *
 */
public class LdapAuthService implements AuthService {

	// ID de la organización que nos da JumpCloud
	private static final String ORG_ID = "616d58fc59fe3e7e11ad6fda";
	
	// Nombre del servidor LDAP
	private static final String LDAP_SERVER = "ldap.jumpcloud.com";
	
	// Puerto del servidor LDAP 
	private static final int LDAP_PORT = 636;
	
	// Indica que se use comunicación por SSL (conexión segura)
	private static final boolean LDAP_USE_SSL = true;
	
	// DN (Distinguished Name) base
	private static final String BASE_DN = "ou=Users,o=" + ORG_ID + ",dc=jumpcloud,dc=com";
	
	// Cadena de bindeo (para intentar iniciar sesión en el servidor LDAP con el usuario indicado)
	private static final String BIND_STRING = "uid=%s," + BASE_DN;

	public boolean login(String username, String password) throws Exception {
		LdapConnection connection = null;
		try {
			connection = new LdapNetworkConnection(LDAP_SERVER, LDAP_PORT, LDAP_USE_SSL);
			connection.setTimeOut(0); // 0 indica que el tiempo de espera del servidor es infinito
			connection.bind(String.format(BIND_STRING, username), password); // inicia sesión en LDAP con username y password 
			return true; // si el "bind" no lanza excepción, es que la autenticación fue correcta
		} catch (LdapAuthenticationException e) {
			return false; // en caso de que la autenticación haya fallado
		} finally {
			connection.close(); // pase lo que pase, cerramos la conexión al servidor LDAP
		}
	}

}
