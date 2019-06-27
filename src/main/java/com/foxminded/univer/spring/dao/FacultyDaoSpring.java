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

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.models.FacultyMapper;

@Component
public class FacultyDaoSpring {
	
	private JdbcTemplate jdbcTemplate;

	private final String SQL_CREATE_FACULTY = "insert into faculties (faculty_name) VALUES (?)";
	private final String SQL_UPDATE_FACULTY = "update faculties set faculty_name = ? where id = ?";
	private final String SQL_DELETE_FACULTY = "delete from faculties where id = ?";
	private final String SQL_FIND_FACULTY = "select * from faculties where id = ?";
	private final String SQL_GET_ALL = "select * from faculties";
	private final String SQL_REMOVE_GROUPS = "update groups set faculty_id = null where faculty_id = ?";
	private final String SQL_REMOVE_TEACHERS = "update teachers set faculty_id = null where faculty_id = ?";

	@Autowired
	public FacultyDaoSpring(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Faculty save(Faculty faculty) throws ClassNotFoundException {
		Faculty facultyToReturn = null;
		if (faculty.getId() == null) {
			facultyToReturn = create(faculty);
		} else {
			facultyToReturn = update(faculty);
		}
		return facultyToReturn;
	}

	public Faculty create(Faculty faculty) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FACULTY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, faculty.getName());
			return statement;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Faculty update(Faculty faculty) {
		jdbcTemplate.update(SQL_UPDATE_FACULTY, faculty.getName(), faculty.getId());
		return findById(faculty.getId());
	}

	public void delete(Faculty faculty) {
		Integer facultyId = faculty.getId();
		removeAllGroupsFromFaculty(facultyId);
        removeAllTeachersFromFaculty(facultyId);
		jdbcTemplate.update(SQL_DELETE_FACULTY, facultyId);
	}

	public Faculty findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_FACULTY, new Object[] { id }, new FacultyMapper());
	}

	public List<Faculty> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new FacultyMapper());
	}
	
	private void removeAllGroupsFromFaculty(Integer facultyId) {
		jdbcTemplate.update(SQL_REMOVE_GROUPS, facultyId);
	}
	
	private void removeAllTeachersFromFaculty(Integer facultyId) {
		jdbcTemplate.update(SQL_REMOVE_TEACHERS, facultyId);
	}
}
