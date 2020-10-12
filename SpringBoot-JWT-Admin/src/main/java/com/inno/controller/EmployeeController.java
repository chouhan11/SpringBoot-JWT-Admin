package com.inno.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inno.entity.Employee;
import com.inno.resource.ResponseMessage;
import com.inno.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	private String mimeType;

	@PostMapping(value = "/emp/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String uploadEmployee(Employee employee, MultipartFile Mfile, MultipartFile Mimage)
			throws SerialException, SQLException, IOException {

		return "Employee successfully uploaded with id:" + service.saveOrUpdate(employee, Mfile, Mimage);
	}

	@GetMapping("/emp/findall")
	public ResponseEntity<List<ResponseMessage>> downloadAllEmployees() {
		return new ResponseEntity<List<ResponseMessage>>(service.getAllEmployee(), HttpStatus.OK);
	}

	@GetMapping("/emp/findbyid/{id}")
	public ResponseEntity<ResponseMessage> downloadEmployeeById(@PathVariable("id") int id) {
		return new ResponseEntity<ResponseMessage>(service.getEmployeeById(id), HttpStatus.OK);
	}

	@GetMapping("/download/file/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		Employee emp = service.downloadByFileName(fileName);
		mimeType = emp.getFContenttype();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.body(new ByteArrayResource(emp.getFile()));
	}

	@GetMapping("/download/image/{fileName:.+}")
	public ResponseEntity<Resource> downloadImage(@PathVariable String fileName) {
		Employee emp = service.downloadByImageName(fileName);
		mimeType = emp.getIContenttype();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.body(new ByteArrayResource(emp.getImage()));

	}

	@PutMapping(value = "/emp/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String editEmployee(Employee employee, MultipartFile Mfile, MultipartFile Mimage) throws IOException {

		return "Employee successfully updated with id:" + service.saveOrUpdate(employee, Mfile, Mimage);
	}

	@DeleteMapping("/emp/deletebyid/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		service.deleteEmployeeById(id);
		return "Employee is deleted with emp-Id : " + id;
	}
}
