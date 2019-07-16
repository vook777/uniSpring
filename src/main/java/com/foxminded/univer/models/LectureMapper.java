package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LectureMapper implements RowMapper<Lecture> {

	@Override
	public Lecture mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Lecture lecture = new Lecture();
		lecture.setId(resultSet.getInt("id"));
		lecture.setTime(resultSet.getTime("time").toLocalTime());
		return lecture;
	}
}
