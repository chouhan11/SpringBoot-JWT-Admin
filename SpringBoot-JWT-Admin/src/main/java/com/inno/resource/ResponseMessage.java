package com.inno.resource;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

	private int empId;
	private String empName;
	private String fileName;
	private String fileContentType;
	private String fileUrl;
	
	private String imageName;
	private String imageContentType;
	private String imageUrl;
}
