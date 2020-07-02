package com.antrl.recognizer.java.parseUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.antrl.recognizer.java.JavaParser;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public abstract class CommonComponent {
	protected String accessModifier;
	protected List<String> modifiersList = new ArrayList();  //para atributos
	protected String type;
	protected String name;

	// Este metodo se usa tanto para atributos, constructores y metodos

	public static void addModifiersMemberDeclaration(CommonComponent commonComponent,
											  JavaParser.MemberDeclarationContext ctx) { // Use un contexto padre tanto de atributos (fiel) constructores
														// y metodos
		int tam = ctx.getParent().children.size();
		// System.out.println("tam "+tam);
		if (tam == 1) { // Si es uno no tiene ningún modificador, sino otra información del nodo, otro
						// hijo
	//setAccessModifier("");
		//this.accessModifier="";

		} else if (tam > 1) {// si es mayor a uno significa que tiene uno o mas modificadores
			for (int i = 0; i < tam - 1; i++) { // Es tam-1 para que no agarre el ultimo hijo que es el propio
												// fielDeclaración, el contexto actual
				String modifier = ctx.getParent().getChild(i).getText();

				if (i == 0) {
					commonComponent.setAccessModifier(verifyAccess(modifier));
					// System.out.println("Modificador de acceso componente: "+
					// commonComponent.getAccesModifier());
					if (!commonComponent.getAccessModifier().equals("")) { // Si no tuviera ningun modificador de
																			// acceso, pero si tiene otro como final o
																			// static, lo saltaria, por eso solo salta
																			// una iteración si ha ocupado un espacio en
																			// el array.
						continue;
					}

				}

			commonComponent.getModifiersList().add(modifier);
				// System.out.println("Otro modificador componente: : "+modifier);
			}

		}
	}

	// Es el mismo método que el de arriba, el problema es el contexto, la
	// navegación cambia, se complica. Por eso meti este sobrecargado. Por más que
	// use un contexto general cambiaria la navegación, si usara un solo metodo.
	public static void addModifiersClassDeclaration(ClassDefinition classDefinition,
											JavaParser.ClassDeclarationContext ctx) {


				int tam = ctx.getParent().children.size();

		if (tam == 1) {
		classDefinition.setAccessModifier("");
		} else if (tam > 1) {
			for (int i = 0; i < tam - 1; i++) {
				String modifier = ctx.getParent().getChild(i).getText();

				if (!modifier.contains("@") && !modifier.contains("final") && !modifier.contains("static") && !modifier.contains("abstract")) {
				classDefinition.setAccessModifier(verifyAccess(modifier));
					// System.out.println("Modificador de acceso componente: "+
					// commonComponent.getAccesModifier());
					if (!classDefinition.getAccessModifier().equals("")) {
						continue;
					}

				}
				if(!modifier.contains("@")){
					classDefinition.getModifiersList().add(modifier);

				}
			}

		}
	}

	public static void addModifierInterfaceDeclaration(InterfaceDefinition interfaceDefinition,
											 JavaParser.InterfaceDeclarationContext ctx) {
		int tam = ctx.getParent().children.size();
		if (tam ==2) {
			interfaceDefinition.setAccessModifier("public");
		}
	}

	public static String verifyAccess(String modifier) {
		if (modifier.contains("private")) {
			return "-";
		}

		else if (modifier.contains("public")) {
			return "+";
		} else if (modifier.contains("protected")) {
			return "#";
		}

		return ""; // package

	}
}
