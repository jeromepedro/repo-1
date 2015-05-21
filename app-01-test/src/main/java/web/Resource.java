package web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Squellette d'un singleton qui gère des paramètres avec rechargement
 * automatique.
 */
public class Resource {

	/**
	 * paramètres issus du fichier "properties" nommé dans la configuration de
	 * Spring
	 */
	private Properties properties; 
	private final static Logger LOGGER = Logger.getLogger(Resource.class);

	public Resource(final String lang) throws IOException{
		try{
		properties = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream("messages_" + lang + ".properties"); 
		properties.load(is);
	}
		catch (IOException e) {
			LOGGER.info("Error while loading resource file");
		}

	}

	public String msg(String propertyName){
		return properties.getProperty(propertyName); 
	}

}
