package com.demo.microservice.stringrepl.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Simple cache for demo.
 * 
 * @author sofia
 * @date 2019-05-11
 */
@Component
public class StringCache {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringCache.class);
	
	private static final int CAP = 5;
	
	private LinkedHashMap<String, String> cache;
	
	public StringCache() {
		cache = new LinkedHashMap<String, String>(2, 0.75f, true) {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
				return cache.size() > CAP;
			}
		};
	}
	
	public String get(String key) {
		LOGGER.info("Cache keyset before get: "+cache.keySet());
		return cache.getOrDefault(key, null);
	}
	
	public void put(String key, String val) {
		cache.put(key, val);
		LOGGER.info("Cache keyset after put: "+cache.keySet());
	}

}
