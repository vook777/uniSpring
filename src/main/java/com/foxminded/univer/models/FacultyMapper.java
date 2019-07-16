package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FacultyMapper implements RowMapper<Faculty> {

	@Override
	public Faculty mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Faculty faculty = new Faculty();
		faculty.setId(resultSet.getInt("id"));
		faculty.setName(resultSet.getString("faculty_name"));
		return faculty;
	}
}
