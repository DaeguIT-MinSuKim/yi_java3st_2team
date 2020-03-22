package yi_java3st_2team.dto;

public class CardInfo {
	private String custName;
	private String div;
	private int count;
	private int check;
	private int credit;
	public CardInfo() {
		
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public CardInfo(String custName, String div, int count) {
		this.custName = custName;
		this.div = div;
		this.count = count;
	}
	public CardInfo(String custName, int check, int credit) {
		this.custName = custName;
		this.check = check;
		this.credit = credit;
	}
	
}
