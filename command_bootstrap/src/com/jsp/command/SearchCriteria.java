package com.jsp.command;

import com.jsp.exception.NotNumberException;

public class SearchCriteria extends Criteria {

	private String searchType="";
	private String keyword="";
	
	
	
	public SearchCriteria() {}
	public SearchCriteria(String pageStr, String perPageNumStr,
						  String searchType, String keyword) 
											throws NotNumberException {
		super(pageStr, perPageNumStr);
		this.keyword = keyword;
		this.searchType = searchType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
