package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import data.sql.Project;
import data.sql.ProjectUser;

@Repository("dao")
@Transactional(propagation = Propagation.REQUIRED)
public class Dao implements IDao {

	private static final Logger LOGGER = Logger.getLogger(Dao.class);

	@PersistenceContext
	private EntityManager entityManager;

	// Handling Project data objects

	@Override
	@SuppressWarnings("unchecked")
	public List<Project> getProjects() {
		return this.entityManager.createQuery("from Project").getResultList();
	}

	// saving Project
	@Override
	public Project saveProject(Project project) {
		this.entityManager.persist(project);
		return project;
	}

	// updating Project
	@Override
	public void updateProject(Project project) {
		this.entityManager.merge(project);
	}

	// deleting Project
	@Override
	public void deleteProject(Integer projectId) {
		Project project = (Project) this.entityManager
				.createQuery("from Project where id=:prjId")
				.setParameter("prjId", projectId).getSingleResult();
		if (null != project) {
			this.entityManager.remove(project);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProjectUser> getProjectUsers() {
		return this.entityManager.createQuery("from ProjectUser")
				.getResultList();

	}

	// saving ProjectUser
	@Override
	@SuppressWarnings("unchecked")
	public void saveProjectUser(ProjectUser projectUser) {
		this.entityManager.persist(projectUser);

	}

	// updating ProjectUser
	@Override
	@SuppressWarnings("unchecked")
	public void updateProjectUser(ProjectUser projectUser) {
		this.entityManager.merge(projectUser);
	}

	// deleting Project
	@Override
	@SuppressWarnings("unchecked")
	public void deleteProjectUser(int id, String cuid) {

		ProjectUser projectUser = (ProjectUser) this.entityManager
				.createQuery(
						"from ProjectUser where projectId=:prjId and cuid=:cuid")
				.setParameter("prjId", id).setParameter("cuid", cuid)
				.getSingleResult();

		if (null != projectUser) {
			this.entityManager.remove(projectUser);
		}

	}
}
