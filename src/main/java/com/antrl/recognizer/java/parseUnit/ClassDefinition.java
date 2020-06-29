package com.antrl.recognizer.java.parseUnit;

import java.util.ArrayList;
import java.util.List;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.member.Annotation;
import com.antrl.recognizer.java.parseUnit.member.Attribute;
import com.antrl.recognizer.java.parseUnit.member.Constructor;
import com.antrl.recognizer.java.parseUnit.member.Implementation;
import com.antrl.recognizer.java.parseUnit.member.Method;

import lombok.Data;

@Data
public class ClassDefinition extends CommonType {

	// El nombre Class no lo permite
	private String _package;

	private List<String> importsList = new ArrayList<>();

	private List<Annotation> annotationsList = new ArrayList<>();

	private List<String> typeParametersList = new ArrayList<>(); // Parametrización de la clase, si es q lo es.

	private String _extends;

	private List<Implementation> implementationsList = new ArrayList<>();

	private List<Attribute> attributesList = new ArrayList<>();

	private List<Constructor> constructorsList = new ArrayList<>();

	private List<Method> methodsList = new ArrayList<>();

	private List<Enum> enumsList = new ArrayList<>();

	private List<ClassDefinition> classesList = new ArrayList<>(); // clases internas comunes o estaticas

	private List<InterfaceDefinition> interfacesList = new ArrayList<>();


	public void addInterface(InterfaceDefinition _interface) {
		interfacesList.add(_interface);
	}

	public void addClass(ClassDefinition ClassDefinition) {
		classesList.add(ClassDefinition);
	}

	public void addAttribute(Attribute attribute) {
		System.out.println(attribute.toString());
		attributesList.add(attribute);
	}

	public void addConstructor(Constructor constructor) {
		System.out.println(constructor);
		constructorsList.add(constructor);
	}

	public void addMethod(Method method) {
		System.out.println(method);
		methodsList.add(method);
	}
	
	public void addAnnotation(Annotation annotation) {
		System.out.println(annotation);
		annotationsList.add(annotation);
	}
	

	public void totalize() {

		System.out.println("\n -------- TOTALIZACIÓN ----------");
		System.out.println(importsList.size() + " importaciones");
		System.out.println(typeParametersList.size() + " parametros de clase");
		System.out.println(implementationsList.size() + " implementaciones");
		System.out.println(attributesList.size() + " atributos");
		System.out.println(constructorsList.size() + " constructores");
		System.out.println(methodsList.size() + " metodos");
		System.out.println(annotationsList.size() + " anotaciones");

	}

	public void addExtend(JavaParser.ClassDeclarationContext ctx) {
		if (ctx.typeType() != null) { // con .equals no funciona
			set_extends(ctx.typeType().classOrInterfaceType().getText());
			System.out.println("Extiende de " + ctx.typeType().classOrInterfaceType().getText());
		}
	}

	public void addImplementations(JavaParser.ClassDeclarationContext ctx) {
		Implementation implementation = null;
		String name = "";

		try {
			// if (ctx.typeList().typeType() != null) { //con .equals no funciona
			int tam = ctx.typeList().typeType().size();
			for (int i = 0; i < tam; i++) {
				implementation = new Implementation();
				name = ctx.typeList().typeType().get(i).classOrInterfaceType().IDENTIFIER().get(0).getText(); // Debo
																												// tomar
																												// el
																												// indice
																												// cero
																												// porque
																												// como
																												// todas
																												// las
																												// producciones
																												// se
																												// usan
																												// para
																												// otras
																												// cosas,
																												// classOrInterfaceType
																												// no es
																												// la
																												// excepcion,
																												// esta
																												// produccion
																												// tes
																												// un
																												// array
																												// de
																												// identificadores.
				implementation.setName(name);
				// System.out.println("nombre imple "+name);
				int tam2 = 0;
				// if (ctx.typeList().typeType().get(i).classOrInterfaceType().typeArguments(0)
				// != null) { //debo comprobar que tenga algo sino me salta el null pointer
				tam2 = ctx.typeList().typeType().get(i).classOrInterfaceType().typeArguments(0).typeArgument().size();

				// }
				for (int j = 0; j < tam2; j++) {
					String typeArgument = ctx.typeList().typeType().get(i).classOrInterfaceType().typeArguments(0)
							.typeArgument().get(j).getText();
					implementation.addTypeArgument(typeArgument);
					// System.out.println(typeArgument);

				}

				implementationsList.add(implementation);
				// System.out.println(implementation.toString());

			}

			// }

		} catch (Exception e) {
			if (!name.equals("")) {
				implementationsList.add(implementation); // añade una implementación no parametrizada, solo el nombre,
															// es para cuando mas arriba salta el error
			} else
				System.out.println("no hay implementación");
		}
	}

	// Este data odria ser sobre cargado para las interfaces o enum, si no ver la
	// navegación, mas adelante
	public void addData(JavaParser.ClassDeclarationContext ctx) {
		addModifiersTypeDeclaration(this, ctx);
		System.out.println("Modificador de Acceso de la clase: " + getAccessModifier());
		setType(ctx.CLASS().getText());
		System.out.println("Es una " + ctx.CLASS().getText());
		setName(ctx.IDENTIFIER().getText());
		System.out.println("Nombre: " + ctx.IDENTIFIER().getText());
		addTypeParameters(ctx);
		addExtend(ctx);
		addImplementations(ctx);
	}

	public void addImport(String _import) {
		importsList.add(_import);
	}

	public void addTypeParameters(JavaParser.ClassDeclarationContext ctx) {
		try {
			int tam = ctx.typeParameters().typeParameter().size();
			for (int i = 0; i < tam; i++) {
				String parameter = ctx.typeParameters().typeParameter().get(i).IDENTIFIER().getText();
				System.out.println("Tiene un parametro de clase de tipo: " + parameter);
				typeParametersList.add(parameter);
			}

		} catch (Exception e) {
			System.out.println("no hay parametrización");
		}

	}

}
