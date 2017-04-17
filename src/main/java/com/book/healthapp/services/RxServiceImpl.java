package com.book.healthapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.healthapp.domain.Rx;
import com.book.healthapp.repositories.RxDAO;

@Service
public class RxServiceImpl implements RxService {

	@Autowired private RxDAO rxDAO;
	
	@Override
	public Iterable<Rx> findRxByDoctorId(int id) {
		return rxDAO.findByDoctorId(id);
	}

	@Override
	public void save(Rx rx) {
		rxDAO.save(rx);
	}

	@Override
	public Iterable<Rx> findRxByPatientId(int id) {
		return rxDAO.findByUserId(id);
	}

}
