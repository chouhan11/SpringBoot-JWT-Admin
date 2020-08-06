package com.mady.pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mady.entity.User;

@RestController
public class UserPaginationController {
	@Autowired
	PaginationService service;

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
			                                         @RequestParam(defaultValue = "3") Integer pageSize,
			                                         @RequestParam(defaultValue = "id") String sortBy) {
		List<User> list = service.getAllUser(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}
}
