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

import com.bilgeadam.SpringBootRestJDBC.model.Ogretmen;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class OgretmenRepo
{
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Ogretmen ogretmen)
	{
		String sql = "INSERT INTO \"OGRETMEN\"(\"NAME\", \"IS_GICIK\") VALUES (:NAME, CAST(:GICIK AS bit))";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", ogretmen.getNAME());
		paramMap.put("IS_GICIK", ogretmen.getIS_GICIK() ? 1 : 0);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean saveEski(Ogretmen ogretmen)
	{
		String sql = "INSERT INTO \"OGRETMEN\"(\"NAME\", \"IS_GICIK\") VALUES (:NAME, CAST(:GICIK AS bit))";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", ogretmen.getNAME());
		paramMap.put("GICIK", ogretmen.getIS_GICIK() ? 1 : 0);
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

	public List<Ogretmen> getAll()
	{
		String sql = "SELECT * FROM \"OGRETME\"";
		RowMapper<Ogretmen> rowMapper = new RowMapper<Ogretmen>()
		{
			@Override
			public Ogretmen mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
			}
		};
		// Incorrect column count: expected 1, actual 3 hatası alır
		// return jdbcTemplate.queryForList(sql, Ogretmen.class);
		// ------------------
		// List<Map<String, Object>> döndürmek ister
		// return jdbcTemplate.queryForList(sql);
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Ogretmen getById(Long id)
	{
		String sql = "SELECT * FROM \"OGRETMEN\" where \"ID\" = :ID";
		RowMapper<Ogretmen> rowMapper = new RowMapper<Ogretmen>()
		{
			@Override
			public Ogretmen mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public Integer deleteById(Long id)
	{
		String sql = "DELETE FROM \"OGRETMEN\" where \"ID\" = :ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, params);
	}
}