package com.mady.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mady.entity.User;
import com.mady.repo.UserRepository;

@RestController
public class UserController {

	@Autowired
	public UserRepository userRepository;
	String likePattern = "a%b%c";
	// But, let's suppose we are doing something more complex.
	// Say we need to fetch the users whose names start with an a, contain b, and
	// end with c.
	// For that, we can add our own LIKE with the Like keyword:
	// And we can then hand in our LIKE pattern when we call the method:

	@GetMapping("/user/like")
	public List<User> getByNameLike() {
		return userRepository.findByNameLike(likePattern);
	}

	// get user by name case sensitive
	@GetMapping("/user/1/{name}")
	public List<User> getByName(@PathVariable("name") String name) {
		return userRepository.findByName(name);
	}

	// get user by name case insensitive
	@GetMapping("/user/2/{name}")
	public List<User> getByNameIgnoreCase(@PathVariable("name") String name) {
		return userRepository.findByNameIgnoreCase(name);
	}

	@GetMapping("/user/3/{name}")
	public List<User> getByNameIs(@PathVariable("name") String name) {
		return userRepository.findByNameIs(name);
	}

	@GetMapping("/user/4/{age}")
	public List<User> getTop3ByAge(@PathVariable("age") int age) {
		return userRepository.findTop3ByAge(age);
	}

	@GetMapping("/user/true")
	public List<User> getByActiveTrue() {
		return userRepository.findByActiveTrue();
	}

	@GetMapping("/user/false")
	public List<User> getByActiveFalse() {
		return userRepository.findByActiveFalse();
	}

	@GetMapping("/user/null")
	public List<User> getByNameIsNull() {
		return userRepository.findByNameIsNull();
	}

	@GetMapping("/user/notnull")
	public List<User> getByNameIsNotNull() {
		return userRepository.findByNameIsNotNull();
	}

	@GetMapping("/user/5/{prefix}")
	public List<User> getByNameStartingWith(@PathVariable("prefix") String prefix) {
		return userRepository.findByNameStartingWith(prefix);
	}

	@GetMapping("/user/6/{suffix}")
	public List<User> getByNameEndingWith(@PathVariable("suffix") String suffix) {
		return userRepository.findByNameEndingWith(suffix);
	}

	@GetMapping("/user/7/{infix}")
	public List<User> getByNameContaining(@PathVariable("infix") String infix) {
		return userRepository.findByNameContaining(infix);
	}

	// Age related method

	@GetMapping("/user/age/less/{less}")
	public List<User> getByAgeLessThan(@PathVariable("less") Integer less) {
		return userRepository.findByAgeLessThan(less);

	}

	@GetMapping("/user/age/lessorequal/{less}")
	public List<User> getByAgeLessThanEqual(@PathVariable("less") Integer less) {
		return userRepository.findByAgeLessThanEqual(less);

	}

	@GetMapping("/user/age/{startAge}/{endAge}")
	public List<User> getByAgeBetween(@PathVariable("startAge") Integer startAge,
			@PathVariable("endAge") Integer endAge) {
		return userRepository.findByAgeBetween(startAge, endAge);

	}

	@GetMapping("/user/age/{ages}") // use comma as separator
	public List<User> getByAgeIn(@PathVariable("ages") Collection<Integer> ages) {
		return userRepository.findByAgeIn(ages);

	}

}
