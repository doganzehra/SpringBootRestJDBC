package com.bilgeadam.SpringBootRestJDBC.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.SpringBootRestJDBC.model.Konu;
import com.bilgeadam.SpringBootRestJDBC.service.KonuService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "konu")
@AllArgsConstructor
public class KonuController
{
	private KonuService repo;

	// localhost:8080/konu/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> getAll()
	{
		return repo.getAll();
	}

	// localhost:8080/konu/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getById(@PathVariable(value = "id") Long id)
	{
		return repo.getById(id);
	}

	// localhost:8080/konu/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Konu konu)
	{
		if (repo.save(konu))
		{
			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	// localhost:8080/konu/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (repo.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
