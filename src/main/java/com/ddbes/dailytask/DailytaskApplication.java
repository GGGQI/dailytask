package com.ddbes.dailytask;

import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class DailytaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailytaskApplication.class, args);
	}
}
