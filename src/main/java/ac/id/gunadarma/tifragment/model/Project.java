package ac.id.gunadarma.tifragment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ac.id.gunadarma.tifragment.StatusEnum;
import ch.genidea.geniweb.base.domain.User;

/**
 * @author ariesp
 */
@Entity
@Table(name = "tifragment_project")
public class Project implements Serializable {

	private static final long serialVersionUID = 8894985001552485023L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid", unique = true)
	private String uuid;

	@Column(name = "name", nullable = false)
	private String projectName;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "status")
	private StatusEnum status;

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Project> subprojects;

	@ManyToOne
	@JoinColumn(name = "fk_project")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "fk_timeFrame")
	private TimeFrame timeFrame;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updateBy;

	public Project() {
	}

	public Project(String uuid, String projectName, String description,
			StatusEnum status, List<Project> subprojects, Project project, User user,
			Date createdDate, Date updateDate, String createdBy, String updateBy) {
		this.uuid = uuid;
		this.projectName = projectName;
		this.description = description;
		this.status = status;
		this.subprojects = subprojects;
		this.project = project;
		this.user = user;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.createdBy = createdBy;
		this.updateBy = updateBy;
		if (project != null) {
			project.subprojects.add(this);
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<Project> getSubprojects() {
		return subprojects;
	}

	public void setSubprojects(List<Project> subprojects) {
		this.subprojects = subprojects;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}