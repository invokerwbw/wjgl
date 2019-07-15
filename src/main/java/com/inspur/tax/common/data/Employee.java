package com.inspur.tax.common.data;

public class Employee {
	
	private int id;

	private String name_En;
	
	private String name_Ch;
	
	private String password;

	private String is_Admin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_En() {
		return name_En;
	}

	public void setName_En(String name_En) {
		this.name_En = name_En;
	}

	public String getName_Ch() {
		return name_Ch;
	}

	public void setName_Ch(String name_Ch) {
		this.name_Ch = name_Ch;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIs_Admin() {
		return is_Admin;
	}

	public void setIs_Admin(String is_Admin) {
		this.is_Admin = is_Admin;
	}
}
