package com.jsp.command;

public class ReplyRemoveCommand {
	private String bno;
	private String rno;
	private String page;
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public int getRnoInt() {
		return Integer.parseInt(rno);
	}
	
	public int getBnoInt() {
		return Integer.parseInt(bno);
	}
	
	public int getPageInt() {
		return Integer.parseInt(page);
	}
	
}








