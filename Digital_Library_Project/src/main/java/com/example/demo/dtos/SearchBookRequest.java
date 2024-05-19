package com.example.demo.dtos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
public class SearchBookRequest {
	
	@NotBlank
	private String searchKey;
	@NotBlank
	private String searchValue;
	@NotBlank
	private String operator;
	
	private static Set<String> allowedKeys = new HashSet<>();
	private static HashMap<String, List<String>> allowedOperatorMap = new HashMap<>();
	SearchBookRequest(){
		allowedKeys.addAll(Arrays.asList("name","author_name","genre","pages","id"));
		allowedOperatorMap.put("name",Arrays.asList("=","like"));
		allowedOperatorMap.put("author_name",Arrays.asList("="));
		allowedOperatorMap.put("pages",Arrays.asList("=","<","<=",">=",">"));
		allowedOperatorMap.put("genre",Arrays.asList("="));
		allowedOperatorMap.put("id",Arrays.asList("="));	
	}
	
	public boolean validate() {
		System.out.println("step - 0.1" + " " + searchKey);
		
		System.out.println(allowedKeys);
		if(!allowedKeys.contains(searchKey)) {
			return false;
		}
		System.out.println("step - 0.2");
		List<String> validOperators =allowedOperatorMap.get(this.searchKey);
		if(!validOperators.contains(this.operator)) {
			return false;
		}
		System.out.println("step - 0.3");
		return true;
	}
}