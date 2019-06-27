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

import com.foxminded.univer.models.Lecture;
import com.foxminded.univer.models.LectureMapper;

@Component
public class LectureDaoSpring {

	private JdbcTemplate jdbcTemplate;

	private final String SQL_CREATE_LECTURE = "insert into lectures (course_id, auditorium_id, teacher_id, group_id, time) VALUES (?, ?, ?, ?, ?)";
	private final String SQL_UPDATE_LECTURE = "update lectures set course_id = ?, auditorium_id = ?, teacher_id = ?, group_id = ?, time = ? where id = ?";
	private final String SQL_DELETE_LECTURE = "delete from lectures where id = ?";
	private final String SQL_FIND_LECTURE = "select * from lectures where id = ?";
	private final String SQL_GET_ALL = "select * from lectures";
	private final String GET_AUDITORIUM_ID = "select auditorium_id from lectures where id = ?";
	private final String GET_COURSE_ID = "select course_id from lectures where id = ?";
	private final String GET_GROUP_ID = "select group_id from lectures where id = ?";
	private final String GET_TEACHER_ID = "select teacher_id from lectures where id = ?";

	@Autowired
	public LectureDaoSpring(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Lecture save(Lecture lecture) throws ClassNotFoundException {
		Lecture lectureToReturn = null;
		if (lecture.getId() == null) {
			lectureToReturn = create(lecture);
		} else {
			lectureToReturn = update(lecture);
		}
		return lectureToReturn;
	}

	public Lecture create(Lecture lecture) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_LECTURE,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, lecture.getCourse().getId());
			statement.setInt(2, lecture.getAuditorium().getId());
			statement.setInt(3, lecture.getTeacher().getId());
			statement.setInt(4, lecture.getGroup().getId());
			statement.setObject(5, lecture.getTime());
			return statement;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Lecture update(Lecture lecture) {
		jdbcTemplate.update(SQL_UPDATE_LECTURE, lecture.getCourse().getId(), lecture.getAuditorium().getId(),
				lecture.getTeacher().getId(), lecture.getGroup().getId(), lecture.getTime(), lecture.getId());
		return findById(lecture.getId());
	}

	public void delete(Lecture lecture) {
		jdbcTemplate.update(SQL_DELETE_LECTURE, lecture.getId());
	}

	public Lecture findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_LECTURE, new Object[] { id }, new LectureMapper());
	}

	public List<Lecture> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new LectureMapper());
	}

	public Integer getAuditoriumId(Integer lectureId) {
		return jdbcTemplate.queryForObject(GET_AUDITORIUM_ID, new Object[] { lectureId }, Integer.class);
	}

	public Integer getCourseId(Integer lectureId) {
		return jdbcTemplate.queryForObject(GET_COURSE_ID, new Object[] { lectureId }, Integer.class);
	}

	public Integer getGroupId(Integer lectureId) {
		return jdbcTemplate.queryForObject(GET_GROUP_ID, new Object[] { lectureId }, Integer.class);
	}

	public Integer getTeacherId(Integer lectureId) {
		return jdbcTemplate.queryForObject(GET_TEACHER_ID, new Object[] { lectureId }, Integer.class);
	}
}
