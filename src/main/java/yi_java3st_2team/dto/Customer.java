package yi_java3st_2team.dto;

public class Customer {
	private String custCode;
	private String custName;
	private String custRank;
	private int custCredit;
	private String custAddr;
	private String custTel;
	private BankBook bankbook;
	public Customer() {
		
	}
	
	public Customer(String custCode, String custName, String custRank, int custCredit, String custAddr,
			String custTel) {
		this.custCode = custCode;
		this.custName = custName;
		this.custRank = custRank;
		this.custCredit = custCredit;
		this.custAddr = custAddr;
		this.custTel = custTel;
	}

	public Customer(String custCode) {
		this.custCode = custCode;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustRank() {
		return custRank;
	}
	public void setCustRank(String custRank) {
		this.custRank = custRank;
	}
	public int getCustCredit() {
		return custCredit;
	}
	public void setCustCredit(int custCredit) {
		this.custCredit = custCredit;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	public String getCustTel() {
		return custTel;
	}
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}
	
}
