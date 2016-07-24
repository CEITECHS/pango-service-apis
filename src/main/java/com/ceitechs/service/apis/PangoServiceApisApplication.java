package com.ceitechs.service.apis;

import com.ceitechs.domain.autoconfigure.EnablePangoDomainService;
import com.ceitechs.domain.service.service.PangoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePangoDomainService
public class PangoServiceApisApplication {

    @Autowired
    PangoDomainService pangoDomainService;

	public static void main(String[] args) {
		SpringApplication.run(PangoServiceApisApplication.class, args);
	}
}


