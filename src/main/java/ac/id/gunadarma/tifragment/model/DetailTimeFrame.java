package ac.id.gunadarma.tifragment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import ac.id.gunadarma.tifragment.StatusEnum;
import ch.genidea.geniweb.base.domain.User;

/**
 * @author ariesp
 */
@Entity
@Table(name = "tifragment_detail_timeframe")
public class DetailTimeFrame implements Serializable {

	private static final long serialVersionUID = 5927760212734413734L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid", unique = true)
	private String uuid;
	
	@Id
	@GeneratedValue (generator="tifragment_sequence")
	@GenericGenerator( name = "tifragment_sequence",
	    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	    parameters = { 
	        @Parameter(name = "sequence_name", value = "tifragment_sequence"), 
	        @Parameter(name = "initial_value", value = "1"), 
	        @Parameter(name = "increment_size", value = "10")})
	@Column ( name = "id", nullable = false )
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "fk_timeframe")
	private TimeFrame timeFrame;
	
	@Column(name = "task_name", length = 20, nullable = false)
	private String taskName;
	
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "status")
	private StatusEnum status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updateBy;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DetailTimeFrame() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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