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
@Table(name="Department",uniqueConstraints = @UniqueConstraint(name="unique",columnNames = "Department"))
public class DepartmentVo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Department")
	private String department;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="CompanyId")
	private CompanyVO com_id;
	
	@OneToMany(mappedBy = "dep_id")
	private List<EmployeeVo> emp ;
	
	@OneToMany(mappedBy = "dep_id",cascade = CascadeType.REMOVE)
	private List<MessageVo> messageid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public CompanyVO getCom_id() {
		return com_id;
	}

	public void setCom_id(CompanyVO com_id) {
		this.com_id = com_id;
	}

	public List<EmployeeVo> getEmp() {
		return emp;
	}

	public void setEmp(List<EmployeeVo> emp) {
		this.emp = emp;
	}

	public List<MessageVo> getMessageid() {
		return messageid;
	}

	public void setMessageid(List<MessageVo> messageid) {
		this.messageid = messageid;
	}
	
}
