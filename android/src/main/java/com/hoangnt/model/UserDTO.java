package com.hoangnt.model;

import java.util.List;

public class UserDTO {
	int id;
	String name;
	String email;
	String password;
	String address;
	String date_of_birth;
	List<ScheduleDTO> scheduleDTOs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public List<ScheduleDTO> getScheduleDTOs() {
		return scheduleDTOs;
	}

	public void setScheduleDTOs(List<ScheduleDTO> scheduleDTOs) {
		this.scheduleDTOs = scheduleDTOs;
	}

}
