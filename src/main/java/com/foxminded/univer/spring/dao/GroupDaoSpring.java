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

import com.foxminded.univer.models.Group;
import com.foxminded.univer.models.GroupMapper;
import com.foxminded.univer.spring.config.DataSourceForJdbcTemplate;

@Component
public class GroupDaoSpring {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private final String SQL_CREATE_GROUP = "insert into groups (group_name, faculty_id) VALUES (?, ?)";
	private final String SQL_UPDATE_GROUP = "update groups set group_name = ?, faculty_id = ? where id = ?";
	private final String SQL_DELETE_GROUP = "delete from groups where id = ?";
	private final String SQL_FIND_GROUP = "select * from groups where id = ?";
	private final String SQL_GET_ALL = "select * from groups";
	private final String SQL_REMOVE_STUDENTS = "update students set group_id = null where group_id = ?";
	private final String FIND_BY_FACULTY_ID = "select * from groups where faculty_id = ?";
	private final String GET_FACULTY_ID = "select faculty_id from groups where id = ?";

	@Autowired
	public GroupDaoSpring(DataSourceForJdbcTemplate ds) {
		dataSource = ds.getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Group save(Group group) throws ClassNotFoundException {
		Group groupToReturn = null;
		if (group.getId() == null) {
			groupToReturn = create(group);
		} else {
			groupToReturn = update(group);
		}
		return groupToReturn;
	}

	public Group create(Group group) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_GROUP,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, group.getName());
			statement.setInt(2, group.getFaculty().getId());
			return statement;
		}, keyHolder);
		Integer id = (Integer) keyHolder.getKeys().get("id");
		return findById(id);
	}

	public Group update(Group group) {
		jdbcTemplate.update(SQL_UPDATE_GROUP, group.getName(), group.getFaculty().getId(), group.getId());
		return findById(group.getId());
	}

	public void delete(Group group) {
		Integer groupId = group.getId();
		removeAllStudentsFromGroup(groupId);
		jdbcTemplate.update(SQL_DELETE_GROUP, groupId);
	}

	public Group findById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_GROUP, new Object[] { id }, new GroupMapper());
	}

	public List<Group> findByFacultyId(Integer facultyId){
		return jdbcTemplate.query(FIND_BY_FACULTY_ID, new GroupMapper());
	}
	
	public List<Group> findAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new GroupMapper());
	}
	
	public Integer getFacultyId(Integer groupId) {
		return jdbcTemplate.queryForObject(GET_FACULTY_ID, new Object[] { groupId }, Integer.class);
	}
	
	private void removeAllStudentsFromGroup(Integer groupId) {
		jdbcTemplate.update(SQL_REMOVE_STUDENTS, groupId);
	}
}
