package yi_java3st_2team.dto;

public class CardInfo {
	private String custname;
	private int transCount;
	public CardInfo() {
		
	}
	public CardInfo(String custname, int transCount) {
		this.custname = custname;
		this.transCount = transCount;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public int getTransCount() {
		return transCount;
	}
	public void setTransCount(int transCount) {
		this.transCount = transCount;
	}
		
}
