package com.antrl.recognizer.java.parseUnit.member;

import lombok.Data;

@Data
public class ElementValuePair {
    private  String name;
    private String value;

    @Override
    public String toString() {
        return "ElementValuePair{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
