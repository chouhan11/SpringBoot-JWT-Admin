package com.mady.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mady.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	// Name related Derived query
	List<User> findByName(String name);

	List<User> findByNameIgnoreCase(String name);

	 List<User> findTop3ByAge(Integer age);

	List<User> findByNameIs(String name);

	List<User> findByNameEquals(String name);

	List<User> findByNameIsNot(String name);

	List<User> findByNameIsNull();

	List<User> findByNameIsNotNull();

	List<User> findByActiveTrue();

	List<User> findByActiveFalse();

	List<User> findByNameStartingWith(String prefix);

	List<User> findByNameEndingWith(String suffix);

	List<User> findByNameContaining(String infix);

	List<User> findByNameLike(String likePattern);

	List<User> findByNameOrderByName(String name);

	List<User> findByNameOrderByNameAsc(String name);

	List<User> findByNameOrderByNameDesc(String name);

	// Age related query
	List<User> findByAgeLessThan(Integer age);

	List<User> findByAgeLessThanEqual(Integer age);

	List<User> findByAgeGreaterThan(Integer age);

	List<User> findByAgeGreaterThanEqual(Integer age);

	List<User> findByAgeBetween(Integer startAge, Integer endAge);

	List<User> findByAgeIn(Collection<Integer> ages);

}
