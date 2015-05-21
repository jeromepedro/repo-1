package business;

/** constantes de configuration de l'application */
public class Configuration {

	/**
	 * indique si l'application est ex�cut�e isol�ment, sans le Guardian/GASSI.
	 * Si true, des contr�les de s�curit�s sont d�sactiv�s et le nom
	 * d'utilisateur est pris dans les param�tres de requ�te plut�t que dans
	 * l'en-t�tes HTTP introduit par le Guardian.
	 */
	static final boolean DEBUG_WEB_LOCAL = false;

	static final int minuteUnit = 60;
	static final int dayUnit = 60 * 60 * 24;
	/**
	 * unit� de temps des param�tres d'expiration de compte. le jour en
	 * production, et la minute en phase de tests.
	 */
	static final int timeUnit = minuteUnit;


	// et pour les tests
}
