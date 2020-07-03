package com.antrl.recognizer.java.parseUnit.member;

import lombok.Data;

@Data
public class ElementValuePair {
    private  String name;
    private String value;
   // private Annotation elementValue; //Para almacenar una anotacion dentro de un valor de un pairvalue, ej inversecolum= Joincolum en el many to many

    @Override
    public String toString() {
        return "ElementValuePair{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
