package com.foxminded.univer.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.AuditoriumDao;
import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;

public class AuditoriumService {

	private AuditoriumDaoSpring auditoriumDao;
	
	public AuditoriumService(AuditoriumDaoSpring ad) {
		this.auditoriumDao = ad;
	}
	
	public Auditorium findById(Integer auditoriumId) throws ClassNotFoundException {
		return auditoriumDao.findById(auditoriumId);
	}
	
	public List<Auditorium> findAll() throws ClassNotFoundException {
		return auditoriumDao.findAll();
	}
}
