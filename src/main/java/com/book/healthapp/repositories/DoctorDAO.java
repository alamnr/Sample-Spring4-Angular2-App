package com.book.healthapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.book.healthapp.domain.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {
	Iterable<Doctor> findBySpecialityCode(String code);
}
