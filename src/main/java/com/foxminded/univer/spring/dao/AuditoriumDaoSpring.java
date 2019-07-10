package com.foxminded.univer.spring.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.models.AuditoriumMapper;
import com.foxminded.univer.spring.config.DataSourceForJdbcTemplate;

public class AuditoriumDaoSpring {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	private final String SQL_CREATE_AUDITORIUM = "insert into auditoriums (name, capacity) VALUES (?, ?)";
	private final String SQL_UPDATE_AUDITORIUM = "update auditoriums set name = ?, capacity = ? where id = ?";
	private final String SQL_DELETE_AUDITORIUM = "delete from auditoriums where id = ?";
	private final String SQL_FIND_AUDITORIUM = "select * from auditoriums where id = ?";
	private final String SQL_GET_ALL = "select * from auditoriums";

	public AuditoriumDaoSpring(DataSourceForJdbcTemplate ds) {
		dataSource = ds.getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Auditorium save(Auditorium auditorium) throws ClassNotFoundException {
		Auditorium auditoriumToReturn = null;
		if (auditorium.getId() == null) {
			auditoriumToReturn = create(auditorium);
		} else {
			auditoriumToReturn = update(auditorium);
		}
		return auditoriumToReturn;
	}
	
	public Auditorium create(Auditorium auditorium) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SQL_CREATE_AUDITORIUM, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, auditorium.getName());
			ps.setInt(2, auditorium.getCapacity());
			return ps;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Auditorium update(Auditorium auditorium) {
		jdbcTemplate.update(SQL_UPDATE_AUDITORIUM, auditorium.getName(), auditorium.getCapacity(), auditorium.getId());
		return findById(auditorium.getId());
	}

	public void delete(Auditorium auditorium) {
		jdbcTemplate.update(SQL_DELETE_AUDITORIUM, auditorium.getId());
	}

	public Auditorium findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_AUDITORIUM, new Object[] { id }, new AuditoriumMapper());
	}

	public List<Auditorium> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new AuditoriumMapper());
	}
}
