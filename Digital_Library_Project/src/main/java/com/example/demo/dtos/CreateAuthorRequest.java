package com.example.demo.dtos;

import com.example.demo.entities.Author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAuthorRequest {
	@NotBlank
	private String name;
	@NotNull
	private String email;
	@NotBlank
	private String country;
	
	public Author to() {
		return Author.builder()
				.name(name)
				.email(email)
				.country(country)
				.build();
	}
}
