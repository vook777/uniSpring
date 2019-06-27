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

import com.foxminded.univer.models.Teacher;
import com.foxminded.univer.models.TeacherMapper;

@Component
public class TeacherDaoSpring {

	private JdbcTemplate jdbcTemplate;

	private final String SQL_CREATE_TEACHER = "insert into teachers (faculty_id, first_name, last_name) VALUES (?, ?, ?)";
	private final String SQL_UPDATE_TEACHER = "update teachers set faculty_id = ?, first_name = ?, last_name = ? where id = ?";
	private final String SQL_DELETE_TEACHER = "delete from teachers where id = ?";
	private final String SQL_FIND_TEACHER = "select * from teachers where id = ?";
	private final String SQL_GET_ALL = "select * from teachers";
	private final String GET_FACULTY_ID = "select faculty_id from teachers where id = ?";

	@Autowired
	public TeacherDaoSpring(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Teacher save(Teacher teacher) throws ClassNotFoundException {
		Teacher teacherToReturn = null;
		if (teacher.getId() == null) {
			teacherToReturn = create(teacher);
		} else {
			teacherToReturn = update(teacher);
		}
		return teacherToReturn;
	}

	public Teacher create(Teacher teacher) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TEACHER,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, teacher.getFaculty().getId());
			statement.setString(2, teacher.getFirstName());
			statement.setString(3, teacher.getLastName());
			return statement;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Teacher update(Teacher teacher) {
		jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getFaculty().getId(), teacher.getFirstName(),
				teacher.getLastName(), teacher.getId());
		return findById(teacher.getId());
	}

	public void delete(Teacher teacher) {
		jdbcTemplate.update(SQL_DELETE_TEACHER, teacher.getId());
	}

	public Teacher findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_TEACHER, new Object[] { id }, new TeacherMapper());
	}

	public List<Teacher> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new TeacherMapper());
	}
	
	public Integer getFacultyId(Integer teacherId) {
		return jdbcTemplate.queryForObject(GET_FACULTY_ID, new Object[] { teacherId }, Integer.class);
	}
}
