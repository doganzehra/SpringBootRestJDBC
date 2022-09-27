package com.bilgeadam.SpringBootRestJDBC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestJDBC.model.Ders;
import com.bilgeadam.SpringBootRestJDBC.repository.DersRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DersService
{
	private DersRepo repo;

	public void saveTransactional()
	{
		try
		{
			Ders ders = new Ders(5L, 3L);
			repo.save(ders);
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
				throw new Exception("Silinirken exception oluştu.");
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean save(Ders ders)
	{
		return repo.save(ders);
	}

	public Ders getById(Long id)
	{
		return repo.getById(id);
	}

	public List<Ders> getAll()
	{
		return repo.getAll();
	}
}