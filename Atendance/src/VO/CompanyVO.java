package VO;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="Company",uniqueConstraints = @UniqueConstraint(name="unique",columnNames = "Email"))
public class CompanyVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="CompanyName")
	private String companyname;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Password")
	private String password;

	@Column(name="Email")
	private String email;
	
	@OneToMany(mappedBy = "com_id" )
	private List<DepartmentVo> DepartmentID ;
	
	@OneToMany(mappedBy = "compnayid" )
	private List<EmployeeVo> EmployeeID ;
	
	@OneToMany(mappedBy = "compnayid" )
	private List<LoginVO> LoginID ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<DepartmentVo> getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(List<DepartmentVo> departmentID) {
		DepartmentID = departmentID;
	}

	public List<EmployeeVo> getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(List<EmployeeVo> employeeID) {
		EmployeeID = employeeID;
	}

	public List<LoginVO> getLoginID() {
		return LoginID;
	}

	public void setLoginID(List<LoginVO> loginID) {
		LoginID = loginID;
	}
	
}
