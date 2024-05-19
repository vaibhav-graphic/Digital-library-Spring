package com.example.demo.dtos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SearchAuthorRequest {
	@NotBlank
	private String searchKey;
	@NotBlank
	private String searchValue;
	
	private static Set<String> allowedKey = new HashSet<>();
	
	public SearchAuthorRequest() {
		allowedKey.addAll(Arrays.asList("name","country","email"));
	}
	
	public boolean isValid() {
		if(!allowedKey.contains(searchKey)) {
			return false;
		}
		return true;
	}
}