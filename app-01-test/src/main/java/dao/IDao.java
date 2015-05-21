package dao;

import java.util.List;

import data.sql.Project;
import data.sql.ProjectUser;

public interface IDao {

	public Project saveProject(Project project);

	public void updateProject(Project project);

	public void deleteProject(Integer projectId);

	public List<Project> getProjects();

	public void saveProjectUser(ProjectUser projectUser);

	public void updateProjectUser(ProjectUser projectUser);

	public void deleteProjectUser(int id, String cuid);

	public List<ProjectUser> getProjectUsers();

}
