package com.bilgeadam.SpringBootRestJDBC.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bilgeadam.SpringBootRestJDBC.model.Konu;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class KonuRepo
{
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Konu konu)
	{
		String sql = "INSERT INTO \"KONU\"( \"NAME\") VALUES (:NAME);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", konu.getNAME());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean saveEski(Konu konu)
	{
		String sql = "INSERT INTO \"KONU\"( \"NAME\") VALUES (:NAME);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", konu.getNAME());
		PreparedStatementCallback<Integer> psc = new PreparedStatementCallback<Integer>()
		{
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
			{
				return ps.executeUpdate();
			}
		};
		return namedParameterJdbcTemplate.execute(sql, paramMap, psc) == 1;
	}

	public List<Konu> getAll()
	{
		String sql = "SELECT * FROM \"KONU\"";
		RowMapper<Konu> rowMapper = new RowMapper<Konu>()
		{
			@Override
			public Konu mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Konu(result.getLong("ID"), result.getString("NAME"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Konu getById(Long id)
	{
		String sql = "SELECT * FROM \"KONU\" where \"ID\" = :ID";
		RowMapper<Konu> rowMapper = new RowMapper<Konu>()
		{
			@Override
			public Konu mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Konu(result.getLong("ID"), result.getString("NAME"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public Integer deleteById(Long id)
	{
		String sql = "DELETE FROM \"KONU\" where \"ID\" = :ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, params);
	}
}