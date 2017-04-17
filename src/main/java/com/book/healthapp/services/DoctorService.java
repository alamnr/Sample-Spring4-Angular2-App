package com.book.healthapp.services;

import java.util.List;

import com.book.healthapp.domain.Doctor;


public interface DoctorService {
	
	void save(Doctor doctor);
	
	Iterable<Doctor> findBySpeciality(String specialityCode);
}
