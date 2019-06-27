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

import com.foxminded.univer.models.Course;
import com.foxminded.univer.models.CourseMapper;

@Component
public class CourseDaoSpring {

	private JdbcTemplate jdbcTemplate;

	private final String SQL_CREATE_COURSE = "insert into courses (name, number_of_weeks, description) VALUES (?, ?, ?)";
	private final String SQL_UPDATE_COURSE = "update courses set name = ?, number_of_weeks = ?, description = ? where id = ?";
	private final String SQL_DELETE_COURSE = "delete from courses where id = ?";
	private final String SQL_FIND_COURSE = "select * from courses where id = ?";
	private final String SQL_GET_ALL = "select * from courses";

	@Autowired
	public CourseDaoSpring(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Course save(Course course) throws ClassNotFoundException {
		Course courseToReturn = null;
		if (course.getId() == null) {
			courseToReturn = create(course);
		} else {
			courseToReturn = update(course);
		}
		return courseToReturn;
	}
	
	public Course create(Course course) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COURSE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, course.getName());
            statement.setInt(2, course.getNumberOfWeeks());
            statement.setString(3, course.getDescription());
			return statement;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Course update(Course course) {
		jdbcTemplate.update(SQL_UPDATE_COURSE, course.getName(), course.getNumberOfWeeks(), course.getDescription(), course.getId());
		return findById(course.getId());
	}

	public void delete(Course course) {
		jdbcTemplate.update(SQL_DELETE_COURSE, course.getId());
	}

	public Course findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_COURSE, new Object[] { id }, new CourseMapper());
	}

	public List<Course> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new CourseMapper());
	}
}
