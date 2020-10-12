package com.inno.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "EMP_TBL")
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String empName;
	private String gender;
	private String dob;
	private String email;
	private String password;
	private long mobNo;
	private String doj;
	private float salary;
	private String addrs;
	private int pincode;
	private boolean Permanent;
	private String country;
	private String state;
	private String city;

	@Lob
	private byte[] file;
	private String fName;
	private String fContenttype;
	private String fUrl;

	@Lob
	private byte[] image;
	private String iName;
	private String iContenttype;
	private String iUrl;

}
