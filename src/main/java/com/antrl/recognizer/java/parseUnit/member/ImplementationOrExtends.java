package com.antrl.recognizer.java.parseUnit.member;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@ToString

//Corresponde a la regla en el Parser o Nodo classOrInterfaceType, es lo mismo tanto para clase como interfaces, pero para clase es una implementación y para interface un extends
public class ImplementationOrExtends {
 protected String name;
protected List<String> typeArguments = new ArrayList<>();
    public void addTypeArgument(String typeArgument) {
        typeArguments.add(typeArgument);
    }

}
