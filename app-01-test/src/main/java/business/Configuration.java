package business;

/** constantes de configuration de l'application */
public class Configuration {

	/**
	 * indique si l'application est exécutée isolément, sans le Guardian/GASSI.
	 * Si true, des contrôles de sécurités sont désactivés et le nom
	 * d'utilisateur est pris dans les paramètres de requête plutôt que dans
	 * l'en-têtes HTTP introduit par le Guardian.
	 */
	static final boolean DEBUG_WEB_LOCAL = false;

	static final int minuteUnit = 60;
	static final int dayUnit = 60 * 60 * 24;
	/**
	 * unité de temps des paramètres d'expiration de compte. le jour en
	 * production, et la minute en phase de tests.
	 */
	static final int timeUnit = minuteUnit;


	// et pour les tests
}
