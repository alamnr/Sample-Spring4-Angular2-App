package com.book.healthapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.healthapp.domain.Doctor;
import com.book.healthapp.helpers.DoctorList;
import com.book.healthapp.repositories.DoctorDAO;
import com.book.healthapp.repositories.RxDAO;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired private DoctorDAO doctorDAO;
	
	@Override
	public Iterable<Doctor> findBySpeciality(String specialityCode) {
		return doctorDAO.findBySpecialityCode(specialityCode);
	}

	@Override
	public void save(Doctor doctor) {
		doctorDAO.save(doctor);
	}
	

}
