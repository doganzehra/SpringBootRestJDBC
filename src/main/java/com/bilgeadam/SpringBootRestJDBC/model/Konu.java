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
public class Konu
{
	private Long ID;

	private String NAME;

	public Konu(String nAME)
	{
		NAME = nAME;
	}
}
