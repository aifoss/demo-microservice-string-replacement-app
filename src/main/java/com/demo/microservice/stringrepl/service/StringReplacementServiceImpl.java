package com.demo.microservice.stringrepl.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.microservice.stringrepl.model.StringReplacementOutput;
import com.demo.microservice.stringrepl.util.StringCache;
import com.google.common.annotations.VisibleForTesting;

/**
 * Implementation of {@link StringReplacementService}.
 * 
 * @author sofia
 * @date 2019-05-11
 */
@Service
public class StringReplacementServiceImpl implements StringReplacementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringReplacementServiceImpl.class);

	@Autowired
	private StringCache stringCache;
	
	@Override
	public StringReplacementOutput replaceAndReverse(String haystack, String needle, String replacement) {
		long start = System.currentTimeMillis();
		long end;
		long processingTime;
        
		String cacheKey = haystack.hashCode()+"-"+needle.hashCode()+"-"+replacement.hashCode();
		LOGGER.info("Cache Key: "+cacheKey);
		
		String resultString = stringCache.get(cacheKey);
		
		if (resultString != null) {
			end = System.currentTimeMillis();
			processingTime = end-start;
			
			LOGGER.info("Cache hit!");
			LOGGER.info("Processing time: "+processingTime);
			
			return new StringReplacementOutput(resultString, processingTime);
		}
		
		resultString = ecalpeResrever(haystack, needle, replacement);
		stringCache.put(cacheKey, resultString);
		
		end = System.currentTimeMillis();
		processingTime = end-start;
		
		LOGGER.info("Cache miss!");
        LOGGER.info("Processing time: "+processingTime);
        
        return new StringReplacementOutput(resultString, processingTime);
	}
	
	@VisibleForTesting
	String ecalpeResrever(String haystack, String needle, String replacement) {
		// special case 1: haystack is empty => return empty string
		// (covered by controller, but left as a test case)
		if (haystack.isEmpty()) {
			return "";
		}
		
		// special case 2: needle is empty => do reversal only
		if (needle.isEmpty()) {
			return reverseWords(haystack);
		}
		
		// special case 3: haystack is shorter than needle => do reversal only
		if (haystack.length() < needle.length()) {
			return reverseWords(haystack);
		}
		
		haystack = replace(haystack, needle, replacement);

        return reverseWords(haystack);
	}
	
	private String replace(String haystack, String needle, String replacement) {
//		return haystack.replaceAll(needle, replacement);
//		return Pattern.compile(needle).matcher(haystack).replaceAll(replacement);
		
		char[] txt = haystack.toCharArray();
		char[] pat = needle.toCharArray();
		
		int m = txt.length;
		int n = pat.length;
		
		List<Integer> indices = doModifiedKMPSearch(txt, pat, m, n);
		
		if (indices.isEmpty()) {
			return haystack;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (indices.get(0) > 0) {
			sb.append(haystack.substring(0, indices.get(0)));
		}
		
		for (int i = 0; i < indices.size(); i++) {
			sb.append(replacement);
			
			int start = indices.get(i)+n;
			int end = i == indices.size()-1 ? m : indices.get(i+1);
			
			if (start < end) {
				sb.append(haystack.substring(start, end));
			}
		}
		
		return sb.toString();
	}
	
	private List<Integer> doModifiedKMPSearch(char[] txt, char[] pat, int m, int n) {
		List<Integer> res = new ArrayList<>();
		
		int[] p = preprocessPattern(pat, n);
		
		int i = 0;
		int j = 0;
		
		while (i < m) {
			while (j >= 0 && pat[j] != txt[i]) {
				j = p[j];
			}
			i++;
			j++;
			
			if (j == n) {
//				res.add(i-n);
				
				int idx = i-n;
				if (res.isEmpty() || (idx >= res.get(res.size()-1)+n)) {
					res.add(i-n);
				}
				
				j = p[j];
			}
		}
		
		return res;
	}
	
	private int[] preprocessPattern(char[] pat, int n) {
		int i = 0;
		int j = -1;
		int[] p = new int[n+1];
		
		p[0] = j;
		
		while (i < n) {
			while (j >= 0 && pat[i] != pat[j]) {
				j = p[j];
			}
			i++;
			j++;
			p[i] = j;
		}
		
		return p;
	}
	
	private String reverseWords(String s) {
		StringBuilder res = new StringBuilder();
		
		String[] words = s.split(" ");
		
		for (String word : words) {
//			if (!word.isEmpty()) {
				res.insert(0,  word+" ");
//			}
		}
		
		res.setLength(res.length()-1);
		
		return res.toString();
	}

}
