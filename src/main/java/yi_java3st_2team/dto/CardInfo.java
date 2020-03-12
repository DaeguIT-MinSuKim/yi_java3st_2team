package yi_java3st_2team.dto;

public class CardInfo {
	private String custname;
	private int check;
	private int credit;
	public CardInfo() {
		
	}
	public CardInfo(String custname, int check, int credit) {
		this.custname = custname;
		this.check = check;
		this.credit = credit;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
}
