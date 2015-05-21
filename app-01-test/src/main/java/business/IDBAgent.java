package business;

import java.util.List;

import data.sql.Project;
import data.sql.ProjectUser;

public interface IDBAgent {

	public List<Project> getAllProjects();

	public Project addProject(Project project);

	public Project updateProject(Project project);

	public void deleteProject(Integer projectId);

	public void addProjectUser(ProjectUser projectUser);

	public List<ProjectUser> getAllProjectUsers();

	public void deleteProjectUser(int id, String cuid);
}
