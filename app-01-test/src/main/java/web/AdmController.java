package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import util.Person;
import business.Application;
import business.IDBAgent;
import data.sql.Project;
import data.sql.ProjectUser;

@Controller
public class AdmController {

	/**
	 * nom de l'en-tête de requête HTTP contenant le nom de l'utilisateur
	 * authentifié par le "Guardian"
	 */
	private static final String username_headername = "sm_universalid";
	private final String globalUsersKey = "adm.global.users";

	private final Application app;
	List<Project> prjList;

	private static final Logger LOGGER = Logger.getLogger(AdmController.class);
	private IDBAgent dbAgent;

	@Autowired
	public AdmController(final Application _app, IDBAgent dbAgent) {

		app = _app;
		this.dbAgent = dbAgent;
	}

	// Formulaire pour l'inscription massive d'utilisateurs

	@RequestMapping(value = "/adm.do", method = { RequestMethod.GET })
	public ModelAndView loadForm(final HttpServletRequest requete,
			final HttpServletResponse reponse) throws IOException {

		String lang = requete.getParameter("lang");
		String username = requete.getHeader(username_headername) != null ? requete
				.getHeader(username_headername) : requete
				.getParameter(username_headername);
		int defaultPrj = 0;

		if (lang == null)
			lang = "fr";
		else if (lang.equalsIgnoreCase("fr"))
			lang = "fr";
		else
			lang = "en";

		HttpSession session = requete.getSession();
		session.setAttribute("resource", new Resource(lang));
		session.setAttribute("lang", lang);
		session.setAttribute("action", "adm");
		session.setAttribute("username", username.toLowerCase());

		if (username == null)
			return new ModelAndView("exception", null);
		else {
			app.reloadParameters();
			String[] globalUserList = app.getParameter(globalUsersKey).split(
					",");
			boolean isGlobalUser = false;

			for (String s : globalUserList) {
				isGlobalUser = isGlobalUser || s.equalsIgnoreCase(username);
			}

			Map<String, Object> data = new HashMap<String, Object>();
			prjList = dbAgent.getAllProjects();

			if (prjList.size() > 0) {
				defaultPrj = prjList.get(0).getId();
			}

			Resource s = new Resource(lang);
			data.put("title", s.msg("form.title"));
			data.put("subtitle", s.msg("form.subtitle.adm_form"));
			data.put("footer_msg", s.msg("form.footer.msg"));
			data.put("footer_url", s.msg("form.footer.url"));
			data.put("version", s.msg("form.version"));
			data.put("prjdata", prjList);
			data.put("defaultPrjId", defaultPrj);
			return new ModelAndView("adm_form", data);
		}

	}

	@RequestMapping(value = "/adm_a.do", method = { RequestMethod.GET })
	public ModelAndView reloadFrm(final HttpServletRequest requete,
			final HttpServletResponse reponse) throws IOException {

		HttpSession session = requete.getSession();
		String lang = requete.getParameter("lang");
		String username = null;
		if (lang == null)
			lang = "fr";
		else if (lang.equalsIgnoreCase("fr"))
			lang = "fr";
		else
			lang = "en";

		username = (String) session.getAttribute("username");
		session.setAttribute("resource", new Resource(lang));
		session.setAttribute("lang", lang);
		session.setAttribute("action", "adm");
		session.setAttribute("username", username);

		if (username == null) {
			reponse.sendRedirect("/otp-accountmanager/adm.do?lang=" + lang);
			return null;

		}

		else {

			int maxUser = 0;
			int defaultPrj = 0;

			app.reloadParameters();
			String[] globalUserList = app.getParameter(globalUsersKey).split(
					",");
			boolean isGlobalUser = false;

			for (String s : globalUserList) {
				isGlobalUser = isGlobalUser || s.equalsIgnoreCase(username);
			}

			Map<String, Object> data = new HashMap<String, Object>();

			prjList = dbAgent.getAllProjects();
			if (prjList.size() > 0) {
				defaultPrj = prjList.get(0).getId();

			}

			Resource s = new Resource(lang);
			data.put("title", s.msg("form.title"));
			data.put("subtitle", s.msg("form.subtitle.adm_form"));
			data.put("footer_msg", s.msg("form.footer.msg"));
			data.put("footer_url", s.msg("form.footer.url"));
			data.put("version", s.msg("form.version"));
			data.put("prjdata", prjList);
			data.put("defaultPrjId", defaultPrj);
			return new ModelAndView("adm_form", data);
		}

	}

	@RequestMapping(value = "/r_adm.do", method = { RequestMethod.GET })
	public void reloadForm(final HttpServletRequest requete,
			final HttpServletResponse reponse) throws IOException {

		HttpSession session = requete.getSession();
		String username = (String) session.getAttribute("username");

		if (requete.getParameter("prj_id") != null
				&& requete.getParameter("role_id") != null && username != null) {

			int projectId = Integer.parseInt(requete.getParameter("prj_id"));

			boolean isValidProjectId = false;
			for (Project p : prjList) {
				isValidProjectId = isValidProjectId || p.getId() == projectId;
			}

			if (!isValidProjectId) {
				LOGGER.error("recuperation des donnees du projet "
						+ requete.getParameter("prj_id")
						+ " non autorisee pour l'utilisateur " + username);
				return;

			}
			int begin = requete.getParameter("start_idx") == null ? 1 : Integer
					.parseInt(requete.getParameter("start_idx"));
			int end = requete.getParameter("end_idx") == null ? 10 : Integer
					.parseInt(requete.getParameter("end_idx"));
			String roleId = requete.getParameter("role_id");

			reponse.setCharacterEncoding("utf-8");
			PrintWriter pw = reponse.getWriter();
			String responseStr = requete.getParameter("prj_id");

			LOGGER.debug(responseStr);
			pw.append(responseStr);
			pw.close();
		}
	}

	@RequestMapping(value = "/addproject.do", method = { RequestMethod.POST })
	public void addProject(final HttpServletRequest requete,
			final HttpServletResponse reponse) {
		HttpSession session = requete.getSession();
		String username = (String) session.getAttribute("username");

		LOGGER.info("ajout projet :" + requete.getParameter("project")
				+ " par utilisateur " + username);
		try {
			PrintWriter pw = reponse.getWriter();

			// On impose que le créateur d'un projet soit membre de la catégorie
			// GIR
			// correspondant aux internes.

			Person p = new Person(username);

			if (p != null) {
				// int projectId = clientDB.createProject(requete
				// .getParameter("project"));
				Project prj = new Project(requete.getParameter("project"));
				prj = dbAgent.addProject(prj);
				int projectId = prj.getId();

				if (projectId > 0) {
					LOGGER.info("projet ajoute - 0 :  " + prj.getId());

					prjList.add(prj);
					LOGGER.info("projet ajoute - 1 :  " + prj.getId());

					ProjectUser u = new ProjectUser(projectId, p.getCuid(),
							"adm");
					LOGGER.info("creation objet projectuser");

					LOGGER.info("ajout " + u.getCuid());
					dbAgent.addProjectUser(u);

					this.logAction(
							(String) requete.getSession().getAttribute(
									"username"),
							"ajout projet '" + requete.getParameter("project")
									+ "' (id : " + projectId + ").");
					pw.append(String.valueOf(projectId));
				} else if (projectId == 0) {
					LOGGER.error("Erreur lors de l'ajout du projet "
							+ requete.getParameter("project")
							+ " - un projet portant le meme nom existe deja.");
					pw.append("0");
				} else
					LOGGER.error("Erreur lors de l'ajout du projet "
							+ requete.getParameter("project")
							+ " - erreur d'acces a la B.D.D.");

			}

			else
				pw.append("null");

			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/editproject.do", method = { RequestMethod.POST })
	public void editProject(final HttpServletRequest requete,
			final HttpServletResponse reponse) throws IOException {

		HttpSession session = requete.getSession();
		final String username = (String) session.getAttribute("username");

		LOGGER.info("modification projet :" + requete.getParameter("prj_id"));
		int projectId = Integer.parseInt(requete.getParameter("prj_id"));

		/*
		 * boolean isValidProjectId = false; for (Project p : prjList) {
		 * isValidProjectId = isValidProjectId || p.getId() == projectId; }
		 * 
		 * if (!isValidProjectId) { LOGGER.error("modification projet " +
		 * requete.getParameter("prj_id") + " non autorisee pour l'utilisateur "
		 * + username); return; }
		 */
		Project p = new Project(projectId, requete.getParameter("name_val"));

		dbAgent.updateProject(p);
		this.logAction(
				(String) requete.getSession().getAttribute("username"),
				"modification projet " + p.getId() + " - nouveau nom : '"
						+ p.getName() + "'.");

	}

	@RequestMapping(value = "/removeproject.do", method = { RequestMethod.POST })
	public void removeProject(final HttpServletRequest requete,
			final HttpServletResponse reponse) throws IOException {
		HttpSession session = requete.getSession();
		final String username = (String) session.getAttribute("username");
		int projectId = Integer.parseInt(requete.getParameter("delete_id"));

		/*
		 * boolean isValidProjectId = false; for (Project p : prjList) {
		 * isValidProjectId = isValidProjectId || p.getId() == projectId; }
		 * 
		 * if (!isValidProjectId) { LOGGER.error("suppression du projet " +
		 * requete.getParameter("prj_id") + " non autorisee pour l'utilisateur "
		 * + username); return; }
		 */

		PrintWriter pw = reponse.getWriter();
		if (projectId > 1) {
			this.logAction(username, "suppression projet " + projectId);
			dbAgent.deleteProjectUser(projectId, username);
			dbAgent.deleteProject(projectId);
			pw.append("ok");
		} else {
			LOGGER.info("suppression projet " + projectId + " non permise");
			pw.append("null");
		}

		pw.close();

	}

	private void logAction(final String smUniversalId, final String action) {
		if (app.admLogEnabled()) {
			FileAppender appender = new FileAppender();
			appender.setName("AdmFileAppender");
			appender.setLayout(new PatternLayout("%d %-5p %m%n"));
			String logDir = app.getAdmLogDir() != null ? app.getAdmLogDir()
					: "../../logs";
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			appender.setFile(logDir + "/adm_"
					+ sdf.format(currentDate.getTime()) + ".log");
			appender.setAppend(true);
			appender.setThreshold(Level.INFO);
			appender.activateOptions();
			LOGGER.addAppender(appender);
			LOGGER.info("ORIGIN : " + smUniversalId + " - ACTION : " + action);
			LOGGER.removeAppender(appender);
		}
	}

}