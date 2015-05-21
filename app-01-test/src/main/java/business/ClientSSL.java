package business;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Logger;

/** un modèle de client SSL qui accepte les certificats de l'IGC interne
	et fournits des enveloppes utiles */ 
public class ClientSSL {
	/** trousseau contenant les certificats auxquels on fait confiance.
	 * Créé à partir de 0 avec une commance du genre keytool -importcert -keystore racine_interne.jks -file <chemin du certificat>
	 * Si on a une AC spécifdique aux serveurs, il vaudrait mieux mettre celle-ci (c'est plus sélectif) */
	public static final String chemin_racine_confiance = "racine_interne.jks";
	/** mot de passe de contrôle d'intégrité du trousseau, pas important tant que le trousseau est inclus dans le code. */
	private static final char [] mot_passe_interne = "racine".toCharArray();
	/** contexte centralisant les paramétrages */
	private static SSLContext contexte;
	
	public static void initParamSSLGlobaux() throws IOException, GeneralSecurityException {
		if (contexte != null) { return; } // pour l'idempotence
		Logger.getLogger(ClientSSL.class).trace("Initialisation des paramètres SSL pour 'HttpsURLConnection'...");
		KeyStore jks = KeyStore.getInstance("JKS");
		InputStream fd = ClassLoader.getSystemResourceAsStream(chemin_racine_confiance);
		jks.load(fd, mot_passe_interne);
		TrustManagerFactory constructeur = TrustManagerFactory.getInstance("PKIX");
		constructeur.init(jks);
		SSLContext ctx = SSLContext.getInstance("TLSv1");
		ctx.init(null, constructeur.getTrustManagers(), new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
		contexte = ctx;
	}
}
