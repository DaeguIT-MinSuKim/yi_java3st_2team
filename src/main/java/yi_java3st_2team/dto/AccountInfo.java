package yi_java3st_2team.dto;

import java.util.Date;

public class AccountInfo {
	private String custname;
	private String accountnum;
	private Date transDate;
	public AccountInfo() {
		
	}
	public AccountInfo(String custname, String accountnum, Date transDate) {
		this.custname = custname;
		this.accountnum = accountnum;
		this.transDate = transDate;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getAccountnum() {
		return accountnum;
	}
	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
}
