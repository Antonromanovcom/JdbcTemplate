package com.antonromanov.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class DaoService {


	private JdbcTemplate template;

	public DaoService(@Autowired DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return template;
	}

	public int getCount() {
		return template.queryForObject("SELECT COUNT(*) FROM public.user", Integer.class);
	}

	public List<User> getAllUsers() {
		final RowMapper<User> userMapper = (rs, i) -> new User(rs.getLong("user_id"),
				rs.getString("name"),
				rs.getString("email"));

		return template.query("SELECT * FROM public.user", userMapper);
	}

	public User addNew(User user) {

		String sql = "insert into  public.user(name, email) values (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.template.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			return statement;
		}, keyHolder);

		long newId;

		if (keyHolder.getKeys().size() > 1) {
			newId = Long.valueOf((Integer)keyHolder.getKeys().get("user_id"));
		} else {
			newId= keyHolder.getKey().longValue();
		}
		user.setId(newId);
		return user;

	}
}
