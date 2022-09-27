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
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.SpringBootRestJDBC.model.Ders;
import com.bilgeadam.SpringBootRestJDBC.model.DersOgrenci;
import com.bilgeadam.SpringBootRestJDBC.model.Ogrenci;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class DersOgrenciRepo
{
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public boolean save(DersOgrenci dersOgrenci)
	{
		String sql = "INSERT INTO \"DERS_OGRENCI\"( \"DERS_ID\", \"OGR_ID\", \"DEVAMSIZLIK\", \"NOTE\") VALUES (:DERS_ID, :OGR_ID, :DEVAMSIZLIK, :NOTE);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("DERS_ID", dersOgrenci.getDERS_ID());
		paramMap.put("OGR_ID", dersOgrenci.getOGR_ID());
		paramMap.put("DEVAMSIZLIK", dersOgrenci.getDEVAMSIZLIK());
		paramMap.put("NOTE", dersOgrenci.getNOTE());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public boolean saveEski(DersOgrenci dersOgrenci)
	{
		String sql = "INSERT INTO \"DERS_OGRENCI\"( \"DERS_ID\", \"OGR_ID\", \"DEVAMSIZLIK\", \"NOTE\") VALUES (:DERS_ID, :OGR_ID, :DEVAMSIZLIK, :NOTE);";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("DERS_ID", dersOgrenci.getDERS_ID());
		paramMap.put("OGR_ID", dersOgrenci.getOGR_ID());
		paramMap.put("DEVAMSIZLIK", dersOgrenci.getDEVAMSIZLIK());
		paramMap.put("NOTE", dersOgrenci.getNOTE());
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

	public List<DersOgrenci> getAll()
	{
		String sql = "SELECT * FROM \"DERS_OGRENCI\" ORDER BY \"ID\" DESC";
		RowMapper<DersOgrenci> rowMapper = new RowMapper<DersOgrenci>()
		{
			@Override
			public DersOgrenci mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new DersOgrenci(result.getLong("ID"), result.getLong("DERS_ID"), result.getLong("OGR_ID"), result.getInt("DEVAMSIZLIK"), result.getInt("NOTE"));
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	public DersOgrenci getById(Long id)
	{
		String sql = "SELECT * FROM \"DERS_OGRENCI\" where \"ID\" = :ID";
		RowMapper<DersOgrenci> rowMapper = new RowMapper<DersOgrenci>()
		{
			@Override
			public DersOgrenci mapRow(ResultSet result, int rowNum) throws SQLException
			{
				return new DersOgrenci(result.getLong("ID"), result.getLong("DERS_ID"), result.getLong("OGR_ID"), result.getInt("DEVAMSIZLIK"), result.getInt("NOTE"));
			}
		};
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
	}

	public Integer deleteById(Long id)
	{
		String sql = "DELETE FROM \"DERS_OGRENCI\" where \"ID\" = :ID";
		HashMap<String, Object> params = new HashMap<>();
		params.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, params);
	}

	@Transactional
	public boolean saveTransactional(Ders ders, Ogrenci ogrenci)
	{
		// try catch yapmayacaksınız !!!
		boolean res = false;
		String sql = "INSERT INTO \"OGRENCI\"( \"NAME\", \"OGR_NUMBER\", \"YEAR\") VALUES (:NAME, :OGR_NUMBER, :YEAR);";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", ogrenci.getNAME());
		paramMap.put("OGR_NUMBER", ogrenci.getOGR_NUMBER());
		paramMap.put("YEAR", ogrenci.getYEAR());
		namedParameterJdbcTemplate.update(sql, paramMap);
		sql = "INSERT INTO public.\"DERS\"( \"OGR_ID\", \"KONU_ID\") VALUES (:OGR_ID, :KONU_ID);";
		paramMap = new HashMap<>();
		paramMap.put("OGR_ID", ders.getOGR_ID());
		paramMap.put("KONU_ID", ders.getKONU_ID());
		namedParameterJdbcTemplate.update(sql, paramMap);
		sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\"(\"DERS_ID\", \"OGR_ID\", \"DEVAMSIZLIK\", \"NOT\") VALUES (:DERS_ID, :OGR_ID, :DEVAMSIZLIK, :NOT)";
		paramMap = new HashMap<>();
		paramMap.put("DERS_ID", 1000);
		paramMap.put("OGR_ID", 1123);
		paramMap.put("DEVAMSIZLIK", 1234);
		paramMap.put("NOT", 1);
		res = namedParameterJdbcTemplate.update(sql, paramMap) > 0;
		return res;
	}

}