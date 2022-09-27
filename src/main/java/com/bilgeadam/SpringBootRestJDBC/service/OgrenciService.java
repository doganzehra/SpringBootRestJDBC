package com.bilgeadam.SpringBootRestJDBC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestJDBC.model.Ogrenci;
import com.bilgeadam.SpringBootRestJDBC.repository.OgrenciRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OgrenciService
{
	private OgrenciRepo repo;

	public void saveTransactional()
	{
		try
		{
			Ogrenci ogrenci = new Ogrenci("ogrenci", 999L, 2L);
			repo.save(ogrenci);
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
				throw new Exception("ID ile silme işleminde hata oluştu");
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean save(Ogrenci ogrenci)
	{
		return repo.save(ogrenci);
	}

	public Ogrenci getById(Long id)
	{
		return repo.getById(id);
	}

	public List<Ogrenci> getAll()
	{
		return repo.getAll();
	}

}