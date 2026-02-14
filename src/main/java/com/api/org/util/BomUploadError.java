package com.api.org.util;

public class BomUploadError {

	public BomUploadError(Integer rowNumber,String col,String message)
	{
		this.rowNumber=rowNumber;
		this.column=col;
		this.message=message;
	}
	 private int rowNumber;
	    private String column;
	    private String message;
	 public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
