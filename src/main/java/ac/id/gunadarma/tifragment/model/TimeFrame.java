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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ac.id.gunadarma.tifragment.StatusEnum;

/**
 * @author ariesp
 */
@Entity
@Table(name = "tifragment_timeframe")
public class TimeFrame implements Serializable {

	private static final long serialVersionUID = 6366840630734528504L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid", unique = true)
	private String uuid;

	@Column(name = "name", nullable = false)
	private String timeFrameName;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "status")
	private StatusEnum status;
	
	@OneToOne(mappedBy = "timeFrame", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Project project;

	@OneToMany(mappedBy = "timeFrame", fetch = FetchType.EAGER, cascade = CascadeType.ALL)	
	private List<DetailTimeFrame> detailTimeFrames;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updateBy;

	public TimeFrame() {
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTimeFrameName() {
		return timeFrameName;
	}

	public void setTimeFrameName(String timeFrameName) {
		this.timeFrameName = timeFrameName;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<DetailTimeFrame> getDetailTimeFrames() {
		return detailTimeFrames;
	}

	public void setDetailTimeFrames(List<DetailTimeFrame> detailTimeFrames) {
		this.detailTimeFrames = detailTimeFrames;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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