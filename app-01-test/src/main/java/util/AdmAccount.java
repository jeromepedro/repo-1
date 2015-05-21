package util;

public class AdmAccount extends Person {

	private String role;
	private int projectId;

	public AdmAccount(final Person p, final String role, final int projectId) {
		super(p.getCuid());
		this.role = role;
		this.projectId = projectId;
	}

	public String getRole() {
		return role;
	}

	public int getProjectId() {
		return projectId;
	}

}