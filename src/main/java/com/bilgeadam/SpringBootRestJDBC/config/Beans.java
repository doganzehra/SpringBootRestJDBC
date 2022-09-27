package com.bilgeadam.SpringBootRestJDBC.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "beans")
public class Beans
{
	@Autowired
	public ApplicationContext applicationContext;

	@Value("${level.low}")
	public String deger;

	@GetMapping
	public ResponseEntity<String> getBeans()
	{
		// localhost:8080/beans
		String[] names = applicationContext.getBeanDefinitionNames();
		Arrays.sort(names);
		StringBuilder builder = new StringBuilder();
		builder.append("value = " + deger + "<br>");
		builder.append("----- " + names.length + " -----").append("<br>");
		for (String name : names)
		{
			builder.append(name + " ---> " + applicationContext.getBean(name).getClass().getName()).append("<br>");
		}
		return ResponseEntity.ok(builder.toString());
	}
}
