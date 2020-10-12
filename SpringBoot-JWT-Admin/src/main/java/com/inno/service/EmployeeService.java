package com.inno.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inno.entity.Employee;
import com.inno.repository.EmployeeRepository;
import com.inno.resource.ResponseMessage;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	public int saveOrUpdate(Employee employee, MultipartFile Mfile, MultipartFile Mimage) throws IOException {

		Employee emp = new Employee();
		employee.setFile(Mfile.getBytes());
		employee.setFContenttype(Mfile.getContentType());
		employee.setFName(Mfile.getOriginalFilename());
		employee.setFUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/file/")
				.path(Mfile.getOriginalFilename()).toUriString());

		employee.setImage(Mimage.getBytes());
		employee.setIContenttype(Mimage.getContentType());
		employee.setIName(Mimage.getOriginalFilename());
		employee.setIUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/image/")
				.path(Mimage.getOriginalFilename()).toUriString());

		BeanUtils.copyProperties(employee, emp);
		Employee saveEmp = repo.save(emp);
		return saveEmp.getId();
	}

	public List<ResponseMessage> getAllEmployee() {
		List<Employee> emplist = repo.findAll();
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		for (Employee emp : emplist) {
			ResponseMessage response = new ResponseMessage(emp.getId(), emp.getEmpName(), emp.getFName(),
					emp.getFContenttype(), emp.getFUrl(), emp.getIName(), emp.getIContenttype(), emp.getIUrl());
			responseMessages.add(response);
		}
		return responseMessages;
	}

	public ResponseMessage getEmployeeById(int id) {
		Optional<Employee> empOptional = repo.findById(id);
		Employee emp = empOptional.get();
		ResponseMessage response = new ResponseMessage(emp.getId(), emp.getEmpName(), emp.getFName(),
				emp.getFContenttype(), emp.getFUrl(), emp.getIName(), emp.getIContenttype(), emp.getIUrl());
		return response;
	}

	public Employee downloadByFileName(String fileName) {
		Employee emp = repo.findByfName(fileName);
		return emp;
	}

	public Employee downloadByImageName(String fileName) {
		Employee emp = repo.findByiName(fileName);
		return emp;
	}

	 public void deleteEmployeeById(int id) {
		 repo.deleteById(id);
	 }
	}
