package com.ceitechs.service.apis.rest.controllers;


import com.ceitechs.service.apis.rest.resources.Models.Rental;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by iddymagohe on 7/21/16.
 */
@RefreshScope
@RestController
public class ReservationRestController{

	@Value("${pango.tagline}")
	private String tagline;

	@RequestMapping(value = "/rentals", method = RequestMethod.GET)
	Collection<Rental> getRentals(){
		return Arrays.asList(tagline.split(",")).stream().map(Rental::new).collect(Collectors.toList());

	}

}