package com.ceitechs.service.apis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@SpringBootApplication
public class PangoServiceApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PangoServiceApisApplication.class, args);
	}
}

