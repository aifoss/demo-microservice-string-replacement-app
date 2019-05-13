package com.demo.microservice.stringrepl.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Unit test for {@link StringReplacementServiceImpl}.
 * 
 * @author sofia
 * @date 2019-05-11
 */
public class StringReplacementServiceImplTest {

	@InjectMocks
	private StringReplacementServiceImpl stringReplacementService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_ecalpeResrever_01() {
		String haystack = "";
		String needle = "needle";
		String replacement = "replacement";
		
		String expected = "";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_02() {
		String haystack = "This is the input string.";
		String needle = "";
		String replacement = "output";
		
		String expected = "string. input the is This";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_08() {
		String haystack = "A B";
		String needle = "AAAAA";
		String replacement = "BB";
		
		String expected = "B A";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_03() {
		String haystack = "This is the input string.";
		String needle = "input";
		String replacement = "";
		
		String expected = "string.  the is This";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_09() {
		String haystack = "ABC DEF GHI";
		String needle = "J";
		String replacement = "KK";
		
		String expected = "GHI DEF ABC";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_04() {
		String haystack = "ABC";
		String needle = "A";
		String replacement = "a";
		
		String expected = "aBC";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_05() {
		String haystack = "AAA AAB BAA";
		String needle = "AA";
		String replacement = "a";
		
		String expected = "Ba aB aA";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_06() {
		String haystack = "I Work.";
		String needle = "Work";
		String replacement = "Play";
		
		String expected = "Play. I";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_07() {
		String haystack = "Tests are the best!";
		String needle = "the best!";
		String replacement = "just ok.";
		
		String expected = "ok. just are Tests";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_10() {
		String haystack = "AAAAAAAAAAAAAAAAAAAA BAA";
		String needle = "AAA";
		String replacement = "a";
		
		String expected = "BAA aaaaaaAA";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_11() {
		String haystack = "AB CAB CDAB CDEAB CDEFAB C";
		String needle = "B C";
		String replacement = "BC";
		
		String expected = "ABCABCDABCDEABCDEFABC";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_12() {
		String haystack = "A B C A B B A BC";
		String needle = "A B ";
		String replacement = "ba";
		
		String expected = "BC A baB baC";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_13() {
		String haystack = "a";
		String needle = "a";
		String replacement = "This is the replacement string.";
		
		String expected = "string. replacement the is This";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}

	@Test
	public void test_ecalpeResrever_14() {
		String haystack = "There are good needles, bad needles, and ugly needles.";
		String needle = "needles, ";
		String replacement = ", NEEDLES";
		
		String expected = "needles. ugly NEEDLESand , NEEDLESbad , good are There";
		String actual = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_ecalpeResrever_15() throws IOException {
		Resource resource = new ClassPathResource("static/sample-input.txt");
		Scanner scanner = new Scanner(resource.getInputStream());
		
		StringBuilder sb = new StringBuilder();
		
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine()+"\n");
		}
		
		scanner.close();
		
		String haystack = sb.toString();
		String needle = "question";
		String replacement = "answer";
		
		String result = stringReplacementService.ecalpeResrever(haystack, needle, replacement);
		
		result = stringReplacementService.ecalpeResrever(result, replacement, needle);
		
		assertEquals(haystack, result);
	}
	
}
