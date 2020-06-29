package com.antrl.recognizer.java.parseUnit.member;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString()
public class Implementation {
	private String name;
	private List<String> typeArguments = new ArrayList<>();

	public void addTypeArgument(String typeArgument) {
		typeArguments.add(typeArgument);
	}

}
