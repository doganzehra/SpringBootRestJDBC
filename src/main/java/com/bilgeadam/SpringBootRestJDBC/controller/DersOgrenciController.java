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

import com.bilgeadam.SpringBootRestJDBC.model.DersOgrenci;
import com.bilgeadam.SpringBootRestJDBC.service.DersOgrenciService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "dersogrenci")
@AllArgsConstructor
public class DersOgrenciController
{
	private DersOgrenciService service;

	// localhost:8080/dersogrenci/getAll
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DersOgrenci> getAll()
	{
		return service.getAll();
	}

	// localhost:8080/dersogrenci/getById/1
	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getById(@PathVariable(value = "id") Long id)
	{
		return service.getById(id);
	}

	// localhost:8080/dersogrenci/save
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody DersOgrenci dersogrenci)
	{
		service.save(dersogrenci);
		return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
//		if (repo.saveEski(dersogrenci))
//		{
//			return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
//		}
//		else
//		{
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
//		}
	}

	// localhost:8080/dersogrenci/deleteById/1
	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id)
	{
		if (service.deleteById(id))
		{
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}
}
