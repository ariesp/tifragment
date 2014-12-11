package ac.id.gunadarma.tifragment.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ProjectForm {

	private String uuid;

	@NotEmpty
	private String name;

	private String description;
	
	@NotEmpty
	private String userUuid;
	
	private String parentUuid;
	
	private String subProject;
	
	private String more;
	
	public ProjectForm() {}
	
	public ProjectForm(String uuid, String name, String description, String userUuid) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.userUuid = userUuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	public String getSubProject() {
		return subProject;
	}

	public void setSubProject(String subProject) {
		this.subProject = subProject;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}		
}