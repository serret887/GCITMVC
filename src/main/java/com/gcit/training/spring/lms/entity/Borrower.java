package com.gcit.training.spring.lms.entity;

import java.io.Serializable;


public class Borrower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2284335440642905082L;
	private int carNo;
	private String name, address;
	private int phone;

	public Borrower() {
	}

	public Borrower(int carNo, String name, String address, int phone) {
		super();
		this.carNo = carNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public int getCarNo() {
		return carNo;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getPhone() {
		return phone;
	}

	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}
