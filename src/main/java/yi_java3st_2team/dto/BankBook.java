package yi_java3st_2team.dto;

import java.util.Date;

public class BankBook {
	private String accountNum;
	private Customer custCode;
	private Plan accountPlanCode;
	private Date accountOpenDate;
	private float accountInterest;
	private long accountBalance;
	public BankBook() {
		
	}
	public BankBook(Customer custCode) {
		this.custCode = custCode;
	}
	
	public BankBook(String accountNum, Customer custCode, Plan accountPlanCode, Date accountOpenDate,
			float accountInterest) {
		super();
		this.accountNum = accountNum;
		this.custCode = custCode;
		this.accountPlanCode = accountPlanCode;
		this.accountOpenDate = accountOpenDate;
		this.accountInterest = accountInterest;
	}
	public BankBook(String accountNum, Customer custCode, Plan accountPlanCode, Date accountOpenDate,
			float accountInterest, long accountBalance) {
		this.accountNum = accountNum;
		this.custCode = custCode;
		this.accountPlanCode = accountPlanCode;
		this.accountOpenDate = accountOpenDate;
		this.accountInterest = accountInterest;
		this.accountBalance = accountBalance;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public Customer getCustCode() {
		return custCode;
	}
	public void setCustCode(Customer custCode) {
		this.custCode = custCode;
	}
	public Plan getAccountPlanCode() {
		return accountPlanCode;
	}
	public void setAccountPlanCode(Plan accountPlanCode) {
		this.accountPlanCode = accountPlanCode;
	}
	public Date getAccountOpenDate() {
		return accountOpenDate;
	}
	public void setAccountOpenDate(Date accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}
	public float getAccountInterest() {
		return accountInterest;
	}
	public void setAccountInterest(float accountInterest) {
		this.accountInterest = accountInterest;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	
}
