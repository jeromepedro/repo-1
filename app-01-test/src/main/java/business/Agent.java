package business;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Squellette d'un singleton qui gère des paramètres avec rechargement
 * automatique.
 */
public class Agent {

	/**
	 * paramètres issus du fichier "properties" nommé dans la configuration de
	 * Spring
	 */
	private Properties properties;
	private String propertiesFilename;
	private final static Logger LOGGER = Logger.getLogger(Agent.class);

	public Agent(final String propertiesFilename) throws IOException {
		this.propertiesFilename = propertiesFilename;
		try {
			properties = new Properties();
			FileInputStream f = new FileInputStream(propertiesFilename);
			properties.load(f);
		} catch (IOException e) {
			LOGGER.error("Error while loading properties file"
					+ propertiesFilename);
		}

	}

	public void reloadParameters() throws IOException {
		try {
			properties = new Properties();
			FileInputStream f = new FileInputStream(this.propertiesFilename);
			properties.load(f);
		} catch (IOException e) {
			LOGGER.error("Error while loading properties file"
					+ this.propertiesFilename);
		}
	}

	public String getParameter(String propertyName) {
		return properties.getProperty(propertyName);
	}

}
