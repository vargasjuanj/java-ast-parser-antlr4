package com.antrl.recognizer.java.parseUnit;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.member.Method;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InterfaceDefinition extends CommonType{
private boolean isFunctional;
    public void addData(JavaParser.InterfaceDeclarationContext ctx) {
        set_package(ctx.getParent().getParent().getChild(0).getText().replaceFirst("package",""));
        System.out.println("Paquete: " + get_package());

        addModifierInterfaceDeclaration(this, ctx);
        System.out.println("Modificador de Acceso de la clase: " + getAccessModifier());
        setType("interface");
        System.out.println("Es una Interfaz");



        if(isFunctional){
            System.out.println("Es Funcional " );

        }else {
            System.out.println("No es Funcional" );

        }

        setName(ctx.IDENTIFIER().getText());
        System.out.println("Nombre: " + ctx.IDENTIFIER().getText());
        addTypeParametersInterfaceDeclaration(ctx);
        addImplementationsOrExtendsList(ctx.typeList());
    }

    public void totalize() {

        System.out.println("\n -------- TOTALIZACIÓN ----------");
        System.out.println(importsList.size() + " importaciones");
        System.out.println(typeParametersList.size() + " parametros de interface");
        System.out.println(implementationOrExtendsList.size() + " extends");
        System.out.println(attributesList.size() + " atributos");
        System.out.println(methodsList.size() + " metodos");
        System.out.println(externalAnnotationsList.size() + " anotaciones externas");

    }

}
