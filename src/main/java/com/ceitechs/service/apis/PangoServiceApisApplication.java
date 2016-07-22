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

@Getter
@Setter
class Rental {

    private Long id;
    private String reservationName;

    public Rental(String reservationName) {
        this.reservationName = reservationName;
        this.id = System.currentTimeMillis();
    }
}

@RefreshScope
@RestController
class ReservationRestController{

	@Value("${pango.tagline}")
	private String tagline;

	@RequestMapping(value = "/rentals", method = RequestMethod.GET)
    Collection<Rental> getRentals(){
		return Arrays.asList(tagline.split(",")).stream().map(Rental::new).collect(Collectors.toList());

	}

}