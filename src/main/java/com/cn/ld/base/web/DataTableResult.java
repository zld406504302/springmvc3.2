package com.cn.ld.base.web;

import java.util.List;

public class DataTableResult<T> {
	int iTotalRecords;
	int iTotalDisplayRecords;
	int displaySize ;
	int displayStart ;
	int internalCounter ;
	String sEcho;
	String sColumns;
	List<String> sortingColumnDefs;
	List<T> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	public int getDisplayStart() {
		return displayStart;
	}

	public void setDisplayStart(int displayStart) {
		this.displayStart = displayStart;
	}

	public List<String> getSortingColumnDefs() {
		return sortingColumnDefs;
	}

	public void setSortingColumnDefs(List<String> sortingColumnDefs) {
		this.sortingColumnDefs = sortingColumnDefs;
	}

	public int getInternalCounter() {
		return internalCounter;
	}

	public void setInternalCounter(int internalCounter) {
		this.internalCounter = internalCounter;
	}

}
