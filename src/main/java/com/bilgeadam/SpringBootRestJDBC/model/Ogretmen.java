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
public class Ogretmen
{
	private Long ID;

	private String NAME;

	private Boolean IS_GICIK;

	public Ogretmen(String nAME, Boolean iS_GICIK)
	{
		NAME = nAME;
		IS_GICIK = iS_GICIK;
	}
}
