package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AuditoriumMapper implements RowMapper<Auditorium> {

	@Override
	public Auditorium mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Auditorium auditorium = new Auditorium();
		auditorium.setId(resultSet.getInt("id"));
		auditorium.setName(resultSet.getString("name"));
		auditorium.setCapacity(resultSet.getInt("capacity"));
		return auditorium;
	}
}
