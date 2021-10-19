package dad.login.auth;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Autenticación de usuarios usando un fichero. El fichero "users.csv" se
 * encuentra en los recursos de la aplicación. Se trata de un fichero CSV (Comma
 * Separated Values), donde cada línea representa un usuario y cada campo se
 * separa mediante una coma (,).
 * 
 * @author fvarrui
 *
 */
public class FileAuthService implements AuthService {

	/**
	 * Autenticación de un usuario mediante nombre de usuario y contraseña.
	 */
	@Override
	public boolean login(String username, String password) throws Exception {

		// cifra la contraseña especificada
		String hashedPassword = hashPassword(password);
		
		// obtiene la ruta al fichero de usuarios
		Path usersFile = Paths.get(getClass().getResource("/users.csv").toURI());
		
		// lee todas las líneas del fichero de usuarios 
		List<String> users = Files.readAllLines(usersFile, StandardCharsets.UTF_8);
		
		// mediante un flujo ...
		return users.stream()
				.map(l -> l.split(","))	// ... descompone línea en un array de Strings a partir de la coma (u[0] = username y u[1] = password)  
				.anyMatch(u  -> u[0].equals(username) && u[1].equals(hashedPassword));	// ... comprueba si alguno casa con el username y el password 

	}
	
	/**
	 * Cifrado de la contraseña usando el algoritmo MD5. 
	 * @param password
	 * @return Password cifrado
	 */
	public String hashPassword(String password) {
		return DigestUtils.md5Hex(password).toUpperCase();
	}

}
