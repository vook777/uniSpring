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

import com.foxminded.univer.models.Student;
import com.foxminded.univer.models.StudentMapper;

@Component
public class StudentDaoSpring {

	private JdbcTemplate jdbcTemplate;

	private final String SQL_CREATE_STUDENT = "insert into students (student_card_number, firstname, lastname, group_id) VALUES (?, ?, ?, ?)";
	private final String SQL_UPDATE_STUDENT = "update students set student_card_number = ?, firstname = ?, lastname = ?, group_id = ? where id = ?";
	private final String SQL_DELETE_STUDENT = "delete from students where id = ?";
	private final String SQL_FIND_STUDENT = "select * from students where id = ?";
	private final String SQL_GET_ALL = "select * from students";
	private final String GET_GROUP_ID = "select group_id from students where id = ?";

	@Autowired
	public StudentDaoSpring(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Student save(Student student) throws ClassNotFoundException {
		Student studentToReturn = null;
		if (student.getId() == null) {
			studentToReturn = create(student);
		} else {
			studentToReturn = update(student);
		}
		return studentToReturn;
	}

	public Student create(Student student) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_STUDENT,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, student.getStudentCardNumber());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getGroup().getId());
			return statement;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Student update(Student student) {
		jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getStudentCardNumber(), student.getFirstName(),
				student.getLastName(), student.getGroup().getId(), student.getId());
		return findById(student.getId());
	}

	public void delete(Student student) {
		jdbcTemplate.update(SQL_DELETE_STUDENT, student.getId());
	}

	public Student findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[] { id }, new StudentMapper());
	}

	public List<Student> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new StudentMapper());
	}
	
	public Integer getGroupId(Integer studentId) {
		return jdbcTemplate.queryForObject(GET_GROUP_ID, new Object[] { studentId }, Integer.class);
	}
}
