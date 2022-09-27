package com.bilgeadam.SpringBootRestJDBC.service;

import java.util.List;

import com.bilgeadam.SpringBootRestJDBC.model.Ders;
import com.bilgeadam.SpringBootRestJDBC.model.DersOgrenci;
import com.bilgeadam.SpringBootRestJDBC.model.Ogrenci;
import com.bilgeadam.SpringBootRestJDBC.repository.DersOgrenciRepo;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class DersOgrenciService
{
	private DersOgrenciRepo repo;

	public void saveTransactional()
	{
		try
		{
			Ogrenci ogrenci = new Ogrenci("güven", 666L, 2L);
			Ders ders = new Ders(9L, 1L);
			repo.saveTransactional(ders, ogrenci);
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
				throw new Exception("Oha lan bu ne");
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void save(DersOgrenci dersogrenci)
	{
		repo.save(dersogrenci);
	}

	public DersOgrenci getById(Long id)
	{
		return repo.getById(id);
	}

	public List<DersOgrenci> getAll()
	{
		return repo.getAll();
	}
}