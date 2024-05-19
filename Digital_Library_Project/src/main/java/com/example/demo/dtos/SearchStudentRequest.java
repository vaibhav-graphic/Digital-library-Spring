package com.example.demo.dtos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SearchStudentRequest {
	private String searchKey;
	private String searchValue;
	
	private static Set<String> allowedKey = new HashSet<>();
	
	public SearchStudentRequest() {
		allowedKey.addAll(Arrays.asList("name","contact","id"));
	}
	
	public boolean isValid() {
		if(!allowedKey.contains(searchKey)) {
			return false;
		}
		return true;
	}
	
}
