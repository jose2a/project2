package com.revature.ctb.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TestRestController {

	@GetMapping("/ctb/")
	@ResponseStatus(code = HttpStatus.OK)
	public String get() {
		return "hello";
	}
}
