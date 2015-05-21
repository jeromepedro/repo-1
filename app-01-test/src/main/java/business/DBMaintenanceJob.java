package business;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Point d'entrée des taches de maintenance de la base de comptes Safenet. Cette
 * classe n'a pas d'état persistant. (imposé par Quartz)
 */
public class DBMaintenanceJob implements Job {

	private final static Logger LOGGER = Logger
			.getLogger(DBMaintenanceJob.class);

	// Class methods

	@Override
	public void execute(final JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			Application app = Application.globalInstance;
			// app.clientDB.autoUpdate();

		} catch (Exception e) {
			LOGGER.error(
					"Erreur lors de la synchronisation annuaire et/ou de la purge des comptes inactifs.",
					e);
		}

	}
}
