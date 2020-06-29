package com.antrl.recognizer.java.parseUnit.member;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class FormalParameter {
	private String type;
	private String name;

}
