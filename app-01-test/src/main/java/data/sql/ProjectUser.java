package data.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "prj_users")
public class ProjectUser {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "PROJECT_ID", nullable = false)
	private int projectId;

	@Column(name = "CUID", length = 8, nullable = false)
	private String cuid;

	@Column(name = "ROLE_ID", length = 8, nullable = false)
	private String roleId;

	// constructors

	public ProjectUser() {
	}

	public ProjectUser(int projectId, String cuid, String roleId) {
		this.projectId = projectId;
		this.cuid = cuid;
		this.roleId = roleId;
	}

	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getCuid() {
		return cuid;
	}

	private void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getRoleId() {
		return roleId;
	}

	private void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
