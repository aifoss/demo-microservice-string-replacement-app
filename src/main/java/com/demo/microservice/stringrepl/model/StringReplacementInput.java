package com.demo.microservice.stringrepl.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Data model to represent input to string replacement method. 
 * 
 * @author sofia
 * @date 2019-05-11
 */
public class StringReplacementInput {

	@NotNull
	@JsonProperty("haystack")
	private String haystack;
	
	@NotNull
	@JsonProperty("needle")
	private String needle;
	
	@NotNull
	@JsonProperty("replacement")
	private String replacement;
	
	public StringReplacementInput() {
		
	}
	
	public StringReplacementInput(String haystack, String needle, String replacement) {
		this.haystack = haystack;
		this.needle = needle;
		this.replacement = replacement;
	}

	public String getHaystack() {
		return haystack;
	}

	public void setHaystack(String haystack) {
		this.haystack = haystack;
	}

	public String getNeedle() {
		return needle;
	}

	public void setNeedle(String needle) {
		this.needle = needle;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}
	
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return "Input[haystack="+haystack+", needle="+needle+", replacement="+replacement+"]";
		}
	}

}
