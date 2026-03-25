package com.example.restframework.api.model;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Integer id;
}