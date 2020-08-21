package VO;

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
@Table(name="Login",uniqueConstraints = @UniqueConstraint(name="unique",columnNames = "Email"))
public class LoginVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;

	@Column(name="Roll")
	private String roll;
	
	@Column(name="Login")
	private String login;
	
	@ManyToOne()
	@JoinColumn(name="CompanyID")
	private CompanyVO compnayid;
	
	@ManyToOne
	@JoinColumn(name="EmployeeId")
	private EmployeeVo employeeid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public CompanyVO getCompnayid() {
		return compnayid;
	}

	public void setCompnayid(CompanyVO compnayid) {
		this.compnayid = compnayid;
	}

	public EmployeeVo getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(EmployeeVo employeeid) {
		this.employeeid = employeeid;
	}

}
