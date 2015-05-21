package business;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Application extends Agent {
	IDBAgent clientDB;
	Scheduler scheduler;
	private static final Logger LOGGER = Logger.getLogger(Application.class);
	static Application globalInstance;

	private final void activate() throws SchedulerException, ParseException {
		configureQuartz();
		assert globalInstance == null;
		globalInstance = this;
	}

	@Autowired
	public Application(final IDBAgent db, final String propertiesFilename)
			throws SchedulerException, ParseException, IOException {
		super(propertiesFilename);

		clientDB = db;
		// activate();
	}

	/** configuration du lanceur de tâches */

	private void configureQuartz() throws ParseException, SchedulerException {

		String t1CS = this.getParameter("db.maintenance.cron.expression");

		JobKey jobKeyA = new JobKey("jobA", "group1");
		JobDetail jobA = JobBuilder.newJob(DBMaintenanceJob.class)
				.withIdentity(jobKeyA).build();

		Trigger trigger1 = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(t1CS)).build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		scheduler.start();
		scheduler.scheduleJob(jobA, trigger1);

	}

	public boolean admLogEnabled() {
		return this.getParameter("adm.log.enabled").equalsIgnoreCase("YES") ? true
				: false;
	}

	public String getAdmLogDir() {
		return this.getParameter("adm.log.dir");
	}

}
