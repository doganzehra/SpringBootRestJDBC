package com.bilgeadam.SpringBootRestJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ogrenci
{
	private Long ID;

	private String NAME;

	private Long OGR_NUMBER;

	private Long YEAR;

	public Ogrenci(String nAME, Long oGR_NUMBER, Long yEAR)
	{
		NAME = nAME;
		OGR_NUMBER = oGR_NUMBER;
		YEAR = yEAR;
	}
}
