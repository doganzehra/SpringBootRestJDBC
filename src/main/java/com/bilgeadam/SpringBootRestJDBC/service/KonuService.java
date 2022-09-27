package com.bilgeadam.SpringBootRestJDBC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestJDBC.model.Konu;
import com.bilgeadam.SpringBootRestJDBC.repository.KonuRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KonuService
{
	private KonuRepo repo;

	public void saveTransactional()
	{
		try
		{
			Konu konu = new Konu("Flutter");
			repo.save(konu);
			// commit

		}
		catch (Exception e)
		{
			// rollback
		}
	}

	public boolean deleteById(Long id)
	{
		try
		{
			Integer result = repo.deleteById(id);
			if (result == 1)
			{
				return true;
			}
			else if (result == 0)
			{
				return false;
			}
			else
			{
				throw new Exception("Id ile konu silme işleminde hata oluştu");
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean save(Konu konu)
	{
		return repo.save(konu);
	}

	public Konu getById(Long id)
	{
		return repo.getById(id);
	}

	public List<Konu> getAll()
	{
		return repo.getAll();
	}

}