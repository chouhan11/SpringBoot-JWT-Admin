package com.mady.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private Integer age;

	private Boolean active;

	// standard getters and setters
}