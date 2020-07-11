package com.antrl.recognizer.java.parseUnit;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import com.antrl.recognizer.java.JavaParser;

import com.antrl.recognizer.java.parseUnit.member.Attribute;
import com.antrl.recognizer.java.parseUnit.member.Constructor;


import lombok.Data;


@Data
public class ClassDefinition extends CommonType {
private boolean isEntity;
	private boolean isAbstract;

	private boolean isFinal;


	private String _extends;


	private List<Constructor> constructorsList = new ArrayList<>();



	public void addConstructor(Constructor constructor) {

		constructorsList.add(constructor);
		System.out.println("*Constructor");
		System.out.println("\tnombre: "+constructor.getName());
		System.out.println("\targumentos: "+constructor.getFormalParametersList());

	}


	

	


	public void addExtend(JavaParser.ClassDeclarationContext ctx) {
		if (ctx.typeType() != null) { // con .equals no funciona
			set_extends(ctx.typeType().classOrInterfaceType().getText());
			//System.out.println("Extiende de " + ctx.typeType().classOrInterfaceType().getText());
		}
	}



	// Este data odria ser sobre cargado para las interfaces o enum, si no ver la
	// navegación, mas adelante
	public void addData(JavaParser.ClassDeclarationContext ctx) {
		setName(ctx.IDENTIFIER().getText());
		System.out.println("*Nombre de la clase: " + ctx.IDENTIFIER().getText());
		set_package(ctx.getParent().getParent().getChild(0).getText().replaceFirst("package",""));
		System.out.println("*Paquete nombre: " + get_package());

		addModifiersClassDeclaration(this, ctx);
		//System.out.println("Modificador de Acceso de la clase: " + getAccessModifier());
		setType(ctx.CLASS().getText());
		//System.out.println("Es una " + ctx.CLASS().getText());
		if(isEntity) {
			//System.out.println("Es una Entidad");
		}
		isFinal();
		if(isAbstract()){
			//System.out.println("Es Abstracta " );

		}else if(isFinal()){
			//System.out.println("Es Final" );

		}


		addTypeParametersClassDeclaration(ctx);
		addExtend(ctx);
		addImplementationsOrExtendsList(ctx.typeList());
	}

	public void filtrarRelaciones() {
		System.out.println("*Relaciones:");
for(int i=0; i<attributesList.size(); i++) {
	if (!attributesList.get(i).getTypeRelation().equals("")) {
		System.out.println("\t"+attributesList.get(i).getTypeRelation() + " con " + attributesList.get(i).getType());
	}
}
	}


	public void totalize() {
filtrarRelaciones();
		System.out.println("\n -------- TOTALIZACIÓN ----------");
		System.out.println(importsList.size() + " importaciones");
		System.out.println(typeParametersList.size() + " tipos genericos de clase");
		System.out.println(implementationOrExtendsList.size() + " implementaciones");
		System.out.println(attributesList.size() + " atributos");
		System.out.println(constructorsList.size() + " constructores");

		System.out.println(methodsList.size() + " metodos");
		System.out.println(externalAnnotationsList.size() + " anotaciones externas");

	}


}
