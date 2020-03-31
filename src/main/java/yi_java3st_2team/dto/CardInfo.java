package yi_java3st_2team.dto;

import java.util.Date;

public class CardInfo {
	private String custName;
	private String div;
	private Date transDate;
	private long cardbalance;
	private int count;
	private int check;
	private int credit;
	public CardInfo() {
		
	}
	
	public Date getTransDate() {
		return transDate;
	}


	public void setTransDate(Date transDate) {
		this.transDate = transDate;
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
	public long getCardbalance() {
		return cardbalance;
	}
	public void setCardbalance(long cardbalance) {
		this.cardbalance = cardbalance;
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
	
	
	
}
