package com.book.healthapp.services;

import java.util.List;

import com.book.healthapp.domain.Rx;

public interface RxService {

	void save(Rx rx);
	
	Iterable<Rx> findRxByDoctorId(int doctorId);
	
	Iterable<Rx> findRxByPatientId(int userId);
}
