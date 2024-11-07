package com.example.program;

import com.example.program.repository.SchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DBCTalendProjectApplication extends SpringBootServletInitializer {
	public static SchemaRepository schemaRepository;

	@Autowired
	public DBCTalendProjectApplication(SchemaRepository schemaRepository) {
		DBCTalendProjectApplication.schemaRepository = schemaRepository;
	}


	public static void main(String[] args) {

		SpringApplication.run(DBCTalendProjectApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WavefrontProperties.Application.class);
	}


}
