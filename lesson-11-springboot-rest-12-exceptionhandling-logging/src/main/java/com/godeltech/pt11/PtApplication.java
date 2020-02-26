package com.godeltech.pt11;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class PtApplication {

//	@GetMapping("/")
//	String home() {
//		return "Spring is here!";
//	}

	public static void main(String[] args) {
		SpringApplication.run(PtApplication.class, args);
	}
}