package com.ceitechs.service.apis;

import com.ceitechs.domain.autoconfigure.EnablePangoDomainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePangoDomainService
public class PangoServiceApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PangoServiceApisApplication.class, args);
	}
}


