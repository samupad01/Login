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

	private static final String ORG_ID = "616d58fc59fe3e7e11ad6fda";
	private static final String LDAP_SERVER = "ldap.jumpcloud.com";
	private static final int LDAP_PORT = 636;
	private static final boolean LDAP_USE_SSL = true;
	private static final String BASE_DN = "ou=Users,o=" + ORG_ID + ",dc=jumpcloud,dc=com";
	private static final String BIND_STRING = "uid=%s," + BASE_DN;

	public boolean login(String username, String password) throws Exception {
		LdapConnection connection = null;
		try {
			connection = new LdapNetworkConnection(LDAP_SERVER, LDAP_PORT, LDAP_USE_SSL);
			connection.setTimeOut(0);
			connection.bind(String.format(BIND_STRING, username), password);
			return true;
		} catch (LdapAuthenticationException e) {
			return false;
		} finally {
			connection.close();
		}
	}

}
