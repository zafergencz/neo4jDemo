package com.example.neo4jDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@ComponentScan({"com.example.controller","com.example.service"})
@EntityScan("com.example.entity")
@EnableNeo4jRepositories("com.example.repository")
public class Neo4jDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(Neo4jDemoApplication.class, args);
	}

}
