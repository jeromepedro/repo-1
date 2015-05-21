package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * Chargement d'un template pour l'envoi des requetes SOAP sur interface Web
 * Service
 **/
public class WSTemplate {

	private String content;
	private final static Logger LOGGER = Logger.getLogger(WSTemplate.class);

	public WSTemplate(String fileName) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				fileName);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		try {
			StringBuilder sb = new StringBuilder();
			int c = isr.read();
			while (c != -1) {
				sb.append((char) c);
				c = isr.read();
			}
			content = sb.toString();
			LOGGER.debug(content);
		}

		catch (IOException e) {
			LOGGER.error("Error while loading template from file " + fileName);
		}

		finally {
			isr.close();
		}
	}

	public String getContent() {
		return content;
	}

}
