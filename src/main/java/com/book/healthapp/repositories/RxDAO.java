package com.book.healthapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.book.healthapp.domain.Rx;

public interface RxDAO extends CrudRepository<Rx, Integer> {
	Iterable<Rx> findByDoctorId(int doctorId);
	
	Iterable<Rx> findByUserId(int userId);
}
