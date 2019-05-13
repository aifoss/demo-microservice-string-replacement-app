package com.demo.microservice.stringrepl.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.microservice.stringrepl.model.StringReplacementInput;
import com.demo.microservice.stringrepl.model.StringReplacementOutput;
import com.demo.microservice.stringrepl.service.StringReplacementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Rest controller for string replacement service application.
 * 
 * @author sofia
 * @date 2019-05-11
 */
@RestController
@RequestMapping("/string-repl")
public class StringReplacementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringReplacementController.class);

	@Autowired
	private StringReplacementService stringReplacementService;
	
	@ApiOperation(
			value = "Replace needle with replacement in haystack and reverse the order of words in haystack", 
			response = StringReplacementOutput.class,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(
			value = "/replace-and-reverse",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StringReplacementOutput> replaceAndReverse(
			@Valid @RequestBody StringReplacementInput request) {
		
		String haystack = request.getHaystack();
		String needle = request.getNeedle();
		String replacement = request.getReplacement();
		
		LOGGER.info("Request for replaceAndReverse:");
		LOGGER.info("haystack: "+haystack);
		LOGGER.info("needle: "+needle);
		LOGGER.info("replacement: "+replacement);
		
		if (haystack.isEmpty()) {
			LOGGER.info("haystack is empty, returning response with empty string ...");
			StringReplacementOutput output = new StringReplacementOutput("", 0);
			ResponseEntity<StringReplacementOutput> response = ResponseEntity.ok(output);
			return response;
		}
		
		return process(haystack, needle, replacement);
	}
	
	@ApiOperation(
			value = "Replace needle with replacement in the content of uploaded file and reverse the order of words", 
			response = StringReplacementOutput.class,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(
			value = "/replace-and-reverse-from-file",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StringReplacementOutput> replaceAndReverseFromFile(
			@Valid @RequestParam(value = "file", required = true) @ApiParam("file in .txt format") MultipartFile file,
			@Valid @RequestParam(value = "needle", required = true) @ApiParam("string to replace") String needle,
			@Valid @RequestParam(value = "replacement", required = true) @ApiParam("string to replace with") String replacement) {

		String fileName = file.getOriginalFilename();
		
		LOGGER.info("Request for replaceAndReverse:");
		LOGGER.info("file: "+fileName);
		LOGGER.info("needle: "+needle);
		LOGGER.info("replacement: "+replacement);
		
		if (file.isEmpty()) {
			LOGGER.info("file is empty, returning response with empty string ...");
			StringReplacementOutput output = new StringReplacementOutput("", 0);
			ResponseEntity<StringReplacementOutput> response = ResponseEntity.ok(output);
			return response;
		}
		
		String haystack = "";
		
		try {
			byte[] bytes = file.getBytes();
			haystack = new String(bytes);
		} catch (IOException e) {
			throw new RuntimeException("Error while reading from input file: "+e.getMessage());
		}
		
		return process(haystack, needle, replacement);
	}
	
	protected ResponseEntity<StringReplacementOutput> process(String haystack, String needle, String replacement) {
		StringReplacementOutput output = stringReplacementService.replaceAndReverse(haystack, needle, replacement);
		
		long processingTime = output.getProcessingTime();
		
		LOGGER.info("Request processed in "+processingTime+" ms");
		LOGGER.info("Returning response ...");
		
		ResponseEntity<StringReplacementOutput> response = ResponseEntity.ok(output);
		
		return response;
	}

}
