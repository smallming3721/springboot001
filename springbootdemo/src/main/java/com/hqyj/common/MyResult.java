package com.hqyj.common;

public class MyResult<T> {

	private int status;
	private String message;
	private T object;
	
	
	
	public MyResult(int status, String message, T object) {
		this.status = status;
		this.message = message;
		this.object = object;
	}
	public MyResult(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public MyResult() {}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
	public enum MyEnum{
		SUCCESS(200),FAILD(500);
		public int status;

		private MyEnum(int status) {
			this.status = status;
		}
	}
	
}
