package VO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Message" ,uniqueConstraints = @UniqueConstraint(name="unique",columnNames = "AttendanceId"))
public class MessageVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Message")
	private String message;
	
	@Column(name = "Status")
	private String status;
	
	@Column(name = "Lasteditedby")
	private String lasteditedby;
	
	@Column(name = "Lastedited")
	private String lastedited;
	

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "DepartmentID")
	private DepartmentVo dep_id;

	@ManyToOne
	@JoinColumn(name = "EmployeeId")
	private EmployeeVo employeeid;
	
	@ManyToOne
	@JoinColumn(name = "AttendanceId")
	private EmpAttendance_outVo attendanceid;
	
	@ManyToOne
	@JoinColumn(name = "CompnayId")
	private CompanyVO companyid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DepartmentVo getDep_id() {
		return dep_id;
	}

	public void setDep_id(DepartmentVo dep_id) {
		this.dep_id = dep_id;
	}

	public EmployeeVo getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(EmployeeVo employeeid) {
		this.employeeid = employeeid;
	}

	public EmpAttendance_outVo getAttendanceid() {
		return attendanceid;
	}

	public void setAttendanceid(EmpAttendance_outVo attendanceid) {
		this.attendanceid = attendanceid;
	}

	public CompanyVO getCompanyid() {
		return companyid;
	}

	public void setCompanyid(CompanyVO companyid) {
		this.companyid = companyid;
	}

	public String getLasteditedby() {
		return lasteditedby;
	}

	public void setLasteditedby(String lasteditedby) {
		this.lasteditedby = lasteditedby;
	}

	public String getLastedited() {
		return lastedited;
	}

	public void setLastedited(String lastedited) {
		this.lastedited = lastedited;
	}
}
