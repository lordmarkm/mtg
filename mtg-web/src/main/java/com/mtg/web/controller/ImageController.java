package com.mtg.web.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/image")
public interface ImageController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<byte[]> image(Long id) throws IOException;
	
}
