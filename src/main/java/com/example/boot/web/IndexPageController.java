package com.example.boot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gimbyeongsu
 * 
 */
@RestController
@RequestMapping("/")
public class IndexPageController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
