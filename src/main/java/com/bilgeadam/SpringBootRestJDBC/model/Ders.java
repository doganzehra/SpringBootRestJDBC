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
public class Ders
{
	private Long ID;

	private Long OGR_ID;

	private Long KONU_ID;
	
	public Ders(Long oGR_ID, Long kONU_ID)
	{
		OGR_ID = oGR_ID;
		KONU_ID = kONU_ID;
	}
}
