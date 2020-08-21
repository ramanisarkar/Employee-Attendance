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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Employee", uniqueConstraints = @UniqueConstraint(name = "unique", columnNames = "Email"))
public class EmployeeVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "Contact_NO")
	private String con_no;

	@Column(name = "Designation")
	private String designation;

	@Column(name = "Address")
	private String address;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Status")
	private String status;

	@Column(name = "Salary")
	private String salary;

	@Column(name = "Roll")
	private String roll;

	@Column(name = "JoiningDate")
	private String joiningdate;

	@ManyToOne()
	@JoinColumn(name = "DepartmentID")
	private DepartmentVo dep_id;

	@ManyToOne()
	@JoinColumn(name = "CompanyID")
	private CompanyVO compnayid;

	@OneToMany(mappedBy = "empattendance", cascade = CascadeType.REMOVE)
	private List<EmpAttendanceVo> attendance;

	@OneToMany(mappedBy = "empattout_id", cascade = CascadeType.REMOVE)
	private List<EmpAttendance_outVo> attendance_out;

	@OneToMany(mappedBy = "employeeid", cascade = CascadeType.REMOVE)
	private List<MessageVo> messageid;

	@OneToMany(mappedBy = "employeeid")
	private List<LoginVO> Loginid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCon_no() {
		return con_no;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public DepartmentVo getDep_id() {
		return dep_id;
	}

	public void setDep_id(DepartmentVo dep_id) {
		this.dep_id = dep_id;
	}

	public List<EmpAttendanceVo> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<EmpAttendanceVo> attendance) {
		this.attendance = attendance;
	}

	public List<EmpAttendance_outVo> getAttendance_out() {
		return attendance_out;
	}

	public void setAttendance_out(List<EmpAttendance_outVo> attendance_out) {
		this.attendance_out = attendance_out;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CompanyVO getCompnayid() {
		return compnayid;
	}

	public void setCompnayid(CompanyVO compnayid) {
		this.compnayid = compnayid;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public List<MessageVo> getMessageid() {
		return messageid;
	}

	public void setMessageid(List<MessageVo> messageid) {
		this.messageid = messageid;
	}

	public List<LoginVO> getLoginid() {
		return Loginid;
	}

	public void setLoginid(List<LoginVO> loginid) {
		Loginid = loginid;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}
}
