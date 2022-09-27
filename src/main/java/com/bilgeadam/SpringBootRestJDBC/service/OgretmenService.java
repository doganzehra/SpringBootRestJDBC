package com.bilgeadam.SpringBootRestJDBC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestJDBC.model.Ogretmen;
import com.bilgeadam.SpringBootRestJDBC.repository.OgretmenRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OgretmenService
{
	private OgretmenRepo repo;

	public void saveTransactional()
	{
		try
		{
			Ogretmen ogretmen = new Ogretmen("ogretmen", true);
			repo.save(ogretmen);
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
				throw new Exception("ID ile öğretmen silme işlemi başarısız.");
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean save(Ogretmen ogretmen)
	{
		return repo.save(ogretmen);
	}

	public Ogretmen getById(Long id)
	{
		return repo.getById(id);
	}

	public List<Ogretmen> getAll()
	{
		return repo.getAll();
	}

}