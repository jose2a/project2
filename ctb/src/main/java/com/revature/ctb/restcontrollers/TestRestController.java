package com.revature.ctb.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {

	@GetMapping("/test")
	@ResponseStatus(code = HttpStatus.OK)
	public String get() {
		return "hello";
	}
}
