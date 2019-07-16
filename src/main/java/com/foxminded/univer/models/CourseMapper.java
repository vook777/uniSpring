package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Course course = new Course();
        course.setId(resultSet.getInt("id"));
        course.setName(resultSet.getString("name"));
        course.setNumberOfWeeks(resultSet.getInt("number_of_weeks"));
        course.setDescription(resultSet.getString("description"));
        return course;
	}
}
