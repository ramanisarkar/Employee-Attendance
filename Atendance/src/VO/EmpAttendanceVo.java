package VO;
import java.sql.Time;
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
@Table(name="EmpAttendance_in")
public class EmpAttendanceVo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="Start")
	private java.sql.Time start;

	
	@Column(name="Date")
	private java.sql.Date date;

	@ManyToOne
	@JoinColumn(name="EmployeeId")
	private EmployeeVo empattendance;
	
	@OneToMany(mappedBy = "attout_id",cascade = CascadeType.REMOVE)
	private List<EmpAttendance_outVo> attendance_out;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
	public EmployeeVo getEmpattendance() {
		return empattendance;
	}

	public void setEmpattendance(EmployeeVo empattendance) {
		this.empattendance = empattendance;
	}

	public List<EmpAttendance_outVo> getAttendance_out() {
		return attendance_out;
	}

	public void setAttendance_out(List<EmpAttendance_outVo> attendance_out) {
		this.attendance_out = attendance_out;
	}
	
}
