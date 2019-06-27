package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getInt("id"));
		student.setStudentCardNumber(resultSet.getString("student_card_number"));
		student.setFirstName(resultSet.getString("firstname"));
		student.setLastName(resultSet.getString("lastname"));
		return student;
	}
}
