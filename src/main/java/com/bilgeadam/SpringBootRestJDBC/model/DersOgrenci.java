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
public class DersOgrenci
{
	private Long ID;

	private Long DERS_ID;

	private Long OGR_ID;

	private Integer DEVAMSIZLIK;

	private Integer NOTE;

	public DersOgrenci(Long dERS_ID, Long oGR_ID, Integer dEVAMSIZLIK, Integer nOTE)
	{
		DERS_ID = dERS_ID;
		OGR_ID = oGR_ID;
		DEVAMSIZLIK = dEVAMSIZLIK;
		NOTE = nOTE;
	}
}
