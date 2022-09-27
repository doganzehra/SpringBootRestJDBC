package com.bilgeadam.SpringBootRestJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.bilgeadam.SpringBootRestJDBC.model.Ders;

@SpringBootApplication
public class SpringBootRestJdbcApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootRestJdbcApplication.class, args);
	}
}