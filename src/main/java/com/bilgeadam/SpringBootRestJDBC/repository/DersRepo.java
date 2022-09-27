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

import com.bilgeadam.SpringBootRestJDBC.model.Ders;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class DersRepo
{
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(Ders ders)
	{
		String sql = "INSERT INTO public.\"DERS\"( \"OGR_ID\", \"KONU_ID\") VALUES (:OGR_ID, :KONU_ID);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("OGR_ID", ders.getOGR_ID());
		paramMap.put("KONU_ID", ders.getKONU_ID());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean saveEski(Ders ders)
	{
		String sql = "INSERT INTO public.\"DERS\"( \"OGR_ID\", \"KONU_ID\") VALUES (:OGR_ID, :KONU_ID);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("OGR_ID", ders.getOGR_ID());
		paramMap.put("KONU_ID", ders.getKONU_ID());
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

	public List<Ders> getAll()
	{
		String sql = "SELECT * FROM \"DERS\"";
		RowMapper<Ders> rowMapper = new RowMapper<Ders>()
		{
			@Override
			public Ders mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Ders(result.getLong("ID"), result.getLong("OGR_ID"), result.getLong("KONU_ID"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Ders getById(Long id)
	{
		String sql = "SELECT * FROM \"DERS\" where \"ID\" = :ID";
		RowMapper<Ders> rowMapper = new RowMapper<Ders>()
		{
			@Override
			public Ders mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new Ders(result.getLong("ID"), result.getLong("OGR_ID"), result.getLong("KONU_ID"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public Integer deleteById(Long id)
	{
		String sql = "DELETE FROM \"DERS\" where \"ID\" = :ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, params);
	}
}