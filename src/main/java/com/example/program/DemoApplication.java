package com.example.program;

import com.example.program.Services.MetaDataService;
import com.example.program.models.MetaDataModel;
import com.example.program.models.Schema;
import com.example.program.repository.SchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@SpringBootApplication
public class DemoApplication {


	public static SchemaRepository schemaRepository;

	@Autowired
	public DemoApplication(SchemaRepository schemaRepository) {
		DemoApplication.schemaRepository = schemaRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


		//System.out.println("-------- Schemas ------------------------------------------------------------");
		//schemaRepository.getSchemas().forEach(e->System.out.println(e.getName()));
//		System.out.println("---------- Tables ------------------------------------------------------------------------");
//		schemaRepository.getTables("spoton").forEach(e->System.out.println(e.getName()));
//		System.out.println("---------- Columns ------------------------------------------------------------------------");
//		schemaRepository.getColumns("spoton","loading").forEach(e->System.out.println(e.getName()));



	}
}
