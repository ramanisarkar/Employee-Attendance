package VO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EmpAttendance_out")
public class EmpAttendance_outVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "End")
	private java.sql.Time end;

	@Column(name = "Status")
	private String status;

	@Column(name = "Date")
	private java.sql.Date date;

	@ManyToOne
	@JoinColumn(name = "EmployeeId")
	private EmployeeVo empattout_id;

	@ManyToOne
	@JoinColumn(name = "EmpAttendanceId")
	private EmpAttendanceVo attout_id;

	@OneToMany(mappedBy = "attendanceid", cascade = CascadeType.REMOVE)
	private List<MessageVo> messageid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Time getEnd() {
		return end;
	}

	public void setEnd(java.sql.Time end) {
		this.end = end;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public EmployeeVo getEmpattout_id() {
		return empattout_id;
	}

	public void setEmpattout_id(EmployeeVo empattout_id) {
		this.empattout_id = empattout_id;
	}

	public EmpAttendanceVo getAttout_id() {
		return attout_id;
	}

	public void setAttout_id(EmpAttendanceVo attout_id) {
		this.attout_id = attout_id;
	}

	public List<MessageVo> getMessageid() {
		return messageid;
	}

	public void setMessageid(List<MessageVo> messageid) {
		this.messageid = messageid;
	}
}
