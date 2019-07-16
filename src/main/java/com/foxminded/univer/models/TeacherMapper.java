package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TeacherMapper implements RowMapper<Teacher> {

	@Override
	public Teacher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getInt("id"));
		teacher.setFirstName(resultSet.getString("first_name"));
		teacher.setLastName(resultSet.getString("last_name"));
		return teacher;
	}
}
