package yi_java3st_2team.dto;

public class Employee {
	private String empCode;
	private String empName;
	private String empTitle;
	private String empAuth;
	private int empSalary;
	private String empTel;
	private String empId;
	private String empPwd;
	private Department dept;
	
	private int perf;
	private int bonus;
	private String vip;
	
	public Employee() {
		
	}
	
	
	
    //실적테이블을 위한 생성자
	public Employee(String empCode, String empName, String empTitle, int perf, int bonus, String vip) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.empTitle = empTitle;
		this.perf = perf;
		this.bonus = bonus;
		this.vip = vip;
	}

    

    // 직급별 테스트용 
	public Employee(String empCode, String empName, String empTitle, String empAuth, int empSalary, String empTel,
			String empId) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.empTitle = empTitle;
		this.empAuth = empAuth;
		this.empSalary = empSalary;
		this.empTel = empTel;
		this.empId = empId;
	}



	public int getPerf() {
		return perf;
	}



	public void setPerf(int perf) {
		this.perf = perf;
	}



	public int getBonus() {
		return bonus;
	}



	public void setBonus(int bonus) {
		this.bonus = bonus;
	}



	public String getVip() {
		return vip;
	}



	public void setVip(String vip) {
		this.vip = vip;
	}



	public Employee(String empCode, String empName, String empTitle, String empAuth, int empSalary, String empTel,
			String empId, String empPwd, Department dept) {
		this.empCode = empCode;
		this.empName = empName;
		this.empTitle = empTitle;
		this.empAuth = empAuth;
		this.empSalary = empSalary;
		this.empTel = empTel;
		this.empId = empId;
		this.empPwd = empPwd;
		this.dept = dept;
	}

	//네개만 있는 emp
	public Employee(String empCode, String empName, String empTitle, String empAuth) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.empTitle = empTitle;
		this.empAuth = empAuth;
	}
	
	

	public Employee(String empCode, String empName, String empTitle, String empAuth, Department dept) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.empTitle = empTitle;
		this.empAuth = empAuth;
		this.dept = dept;
	}



	public Employee(String empId, String empPwd) {
		this.empId = empId;
		this.empPwd = empPwd;
	}

	
	
	public Employee(String empName) {
		super();
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpTitle() {
		return empTitle;
	}
	public void setEmpTitle(String empTitle) {
		this.empTitle = empTitle;
	}
	public String getEmpAuth() {
		return empAuth;
	}
	public void setEmpAuth(String empAuth) {
		this.empAuth = empAuth;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}
	public String getEmpTel() {
		return empTel;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpPwd() {
		return empPwd;
	}
	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
}
