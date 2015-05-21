package business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.IDao;
import data.sql.Project;
import data.sql.ProjectUser;

@Component
public class DBAgent implements IDBAgent {

	@Autowired
	public IDao dao;

	@Override
	@Transactional
	public List<Project> getAllProjects() {
		return dao.getProjects();
	}

	@Override
	@Transactional
	public Project addProject(Project project) {
		dao.saveProject(project);
		return project;
	}

	@Override
	@Transactional
	public Project updateProject(Project project) {
		dao.updateProject(project);
		return project;
	}

	@Override
	@Transactional
	public void deleteProject(Integer projectId) {
		dao.deleteProject(projectId);
	}

	@Override
	@Transactional
	public void addProjectUser(ProjectUser projectUser) {
		dao.saveProjectUser(projectUser);
	}

	@Override
	@Transactional
	public List<ProjectUser> getAllProjectUsers() {
		return dao.getProjectUsers();
	}

	@Override
	@Transactional
	public void deleteProjectUser(int id, String cuid) {
		dao.deleteProjectUser(id, cuid);
	}

	/*
	 * @Autowired public void setDao(IDao dao) { this.dao = dao; }
	 */

}
