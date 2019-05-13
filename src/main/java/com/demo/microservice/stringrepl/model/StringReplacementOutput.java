package com.demo.microservice.stringrepl.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Data model to encapsulate string replacement result with processing time.
 * 
 * @author sofia
 * @date 2019-05-11
 */
public class StringReplacementOutput {

	@NotNull
	@JsonProperty("result-string")
	private String resultString;
	
	@NotNull
	@JsonProperty("processing-time")
	private long processingTime;
	
	public StringReplacementOutput() {
		
	}
	
	public StringReplacementOutput(String resultString, long processingTime) {
		this.resultString = resultString;
		this.processingTime = processingTime;
	}
	
	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public long getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return "ActionResult[resultString="+resultString+", processingTime="+processingTime+" ms]";
		}
	}

}
