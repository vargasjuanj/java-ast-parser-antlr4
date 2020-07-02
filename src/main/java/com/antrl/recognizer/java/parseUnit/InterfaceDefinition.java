package com.antrl.recognizer.java.parseUnit;

import com.antrl.recognizer.java.JavaParser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InterfaceDefinition extends CommonType{
    private List<String> extendsList= new ArrayList();
    public void addData(JavaParser.InterfaceDeclarationContext ctx) {
        set_package(ctx.getParent().getParent().getChild(0).getText().replaceFirst("package",""));
        System.out.println("Paquete: " + get_package());

        addModifierInterfaceDeclaration(this, ctx);
        if(getAccessModifier()==null){
            setAccessModifier("");
        }
        System.out.println("Modificador de Acceso de la interface: " + getAccessModifier());

        setName(ctx.IDENTIFIER().getText());
        System.out.println("Nombre: " + ctx.IDENTIFIER().getText());
        addTypeParametersInterfaceDeclaration(ctx);
        //addExtend(ctx);
        //addImplementations(ctx);
    }



    public void totalize() {

        System.out.println("\n -------- TOTALIZACIÓN ----------");
        System.out.println(importsList.size() + " importaciones");
        System.out.println(typeParametersList.size() + " parametros de interface");
      // System.out.println(implementationsList.size() + " implementaciones");
        System.out.println(attributesList.size() + " atributos");
        System.out.println(methodsList.size() + " metodos");
        System.out.println(annotationsList.size() + " anotaciones");

    }

    public void addExtend(JavaParser.InterfaceDeclarationContext ctx) {
        if(ctx.typeList().typeType().size()>=1){
            for (int i=0; i<ctx.typeList().typeType().size(); i++){
                extendsList.add(ctx.typeList().typeType().get(i).classOrInterfaceType().getText());
                System.out.println("Extiende de " + ctx.typeList().typeType().get(i).classOrInterfaceType().getText());

            }
        }
    }

}
