package com.foxminded.univer.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupMapper implements RowMapper<Group> {

	@Override
	public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Group group = new Group();
		group.setId(resultSet.getInt("id"));
		group.setName(resultSet.getString("group_name"));
		return group;
	}
}
