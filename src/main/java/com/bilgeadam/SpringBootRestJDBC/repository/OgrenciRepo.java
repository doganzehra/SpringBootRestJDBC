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

import com.bilgeadam.SpringBootRestJDBC.model.Ogrenci;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class OgrenciRepo
{
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Ogrenci ogrenci)
	{
		String sql = "INSERT INTO \"OGRENCI\"( \"NAME\", \"OGR_NUMBER\", \"YEAR\") VALUES (:NAME, :OGR_NUMBER, :YEAR);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", ogrenci.getNAME());
		paramMap.put("OGR_NUMBER", ogrenci.getOGR_NUMBER());
		paramMap.put("YEAR", ogrenci.getYEAR());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean saveEski(Ogrenci ogrenci)
	{
		String sql = "INSERT INTO \"OGRENCI\"( \"NAME\", \"OGR_NUMBER\", \"YEAR\") VALUES (:NAME, :OGR_NUMBER, :YEAR);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("NAME", ogrenci.getNAME());
		paramMap.put("OGR_NUMBER", ogrenci.getOGR_NUMBER());
		paramMap.put("YEAR", ogrenci.getYEAR());
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

	public List<Ogrenci> getAll()
	{
		String sql = "SELECT * FROM \"OGRENCI\"";
		RowMapper<Ogrenci> rowMapper = new RowMapper<Ogrenci>()
		{
			@Override
			public Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("OGR_NUMBER"), result.getLong("YEAR"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Ogrenci getById(Long id)
	{
		String sql = "SELECT * FROM \"OGRENCI\" where \"ID\" = :ID";
		RowMapper<Ogrenci> rowMapper = new RowMapper<Ogrenci>()
		{
			@Override
			public Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("OGR_NUMBER"), result.getLong("YEAR"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public Integer deleteById(Long id)
	{
		String sql = "DELETE FROM \"OGRENCI\" where \"ID\" = :ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, params);
	}
}