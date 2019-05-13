package com.demo.microservice.stringrepl.service;

import com.demo.microservice.stringrepl.model.StringReplacementOutput;

/**
 * Interface declaring methods for string replacement.
 * 
 * @author sofia
 * @date 2019-05-11
 */
public interface StringReplacementService {

	/**
	 * Returns a string that results from replacing needle with replacement in haystack and then reversing the order of words in haystack,
	 * together with processing time.
	 * 
	 * @param haystack source input string 
	 * @param needle string to replace
	 * @param replacement string to replace needle with
	 * @return {@link StringReplacementOutput} object containing transformed haystack string after replacement and reversal and processing time
	 */
	StringReplacementOutput replaceAndReverse(String haystack, String needle, String replacement);

}
