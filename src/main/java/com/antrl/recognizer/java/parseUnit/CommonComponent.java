package com.antrl.recognizer.java.parseUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.antrl.recognizer.java.JavaParser;

import com.antrl.recognizer.java.parseUnit.member.Annotation;
import com.antrl.recognizer.java.parseUnit.member.Attribute;
import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public abstract class CommonComponent {
	protected boolean oneToOne;
	protected boolean oneToMany;
	private boolean manyToMany;
	private boolean manyToOne;

	protected  String relationWithType;
	protected List<Annotation> annotationsList = new ArrayList<>();

	//protected Annotation annotation;
	//protected Annotation annotationAux;
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

		//System.out.println("ATRIBUTO ANOTACION "+ctxClassOrInterfaceModifier.getChild(0).getText());
		if (tam == 1) { // Si es uno no tiene ningún modificador, sino otra información del nodo, otro
						// hijo
	commonComponent.setAccessModifier("");


		} else if (tam > 1) {// si es mayor a uno significa que tiene uno o mas modificadores
			for (int i = 0; i < tam - 1; i++) { // Es tam-1 para que no agarre el ultimo hijo que es el propio
												// fielDeclaración, el contexto actual
				JavaParser.ClassOrInterfaceModifierContext ctxClassOrInterfaceModifier= (JavaParser.ClassOrInterfaceModifierContext) ctx.getParent().getChild(i).getChild(0);
				String modifier = ctx.getParent().getChild(i).getText();


				if (!modifier.contains("@") && !modifier.contains("final") && !modifier.contains("static") && !modifier.contains("abstract")) {
					commonComponent.setAccessModifier(verifyAccess(modifier));
					// System.out.println("Modificador de acceso componente: "+
					// commonComponent.getAccesModifier());
					if (!commonComponent.getAccessModifier().equals("")) {
						continue;
					}

				}
if(!modifier.startsWith("@")){

	commonComponent.getModifiersList().add(modifier);

}else {
	System.out.println("entre");
	commonComponent.getAnnotationsList().add((CommonType.addAnnotation(ctxClassOrInterfaceModifier)));

	if(ctxClassOrInterfaceModifier.annotation().qualifiedName().getText().startsWith("OneToOne")){
  	commonComponent.setOneToOne(true);
		commonComponent.relationWithType=commonComponent.type;
  }else if(ctxClassOrInterfaceModifier.annotation().qualifiedName().getText().startsWith("OneToMany")) {
	  commonComponent.setOneToMany(true);
		commonComponent.relationWithType=commonComponent.type.replaceFirst("List<", "");
		commonComponent.relationWithType=commonComponent.relationWithType.replaceFirst(">","");
  }else if (ctxClassOrInterfaceModifier.annotation().qualifiedName().getText().startsWith("ManyToMany")) {
	  commonComponent.setManyToMany(true);
		commonComponent.relationWithType=commonComponent.type.replaceFirst("List<", "");
		commonComponent.relationWithType=commonComponent.relationWithType.replaceFirst(">","");
  }else if (ctxClassOrInterfaceModifier.annotation().qualifiedName().getText().startsWith("ManyToOne")) {
		commonComponent.setManyToOne(true);
		commonComponent.relationWithType=commonComponent.type.replaceFirst("List<", "");
		commonComponent.relationWithType=commonComponent.relationWithType.replaceFirst(">","");
	}

/*
	  try {
			JavaParser.ClassOrInterfaceModifierContext ctxClassOrInterFaceModifierAux = (JavaParser.ClassOrInterfaceModifierContext) ctx.getParent().getChild(i+1).getChild(0);
			if (ctxClassOrInterFaceModifierAux.annotation().qualifiedName().getText().startsWith("JoinColumn") || ctxClassOrInterFaceModifierAux.annotation().qualifiedName().getText().startsWith("JoinTable")) {
				commonComponent.setAnnotationAux(CommonType.addAnnotation(ctxClassOrInterFaceModifierAux));
			}
		} catch (Exception e) {

		}
*/


}
				// System.out.println("Otro modificador componente: : "+modifier);
			}

		}
	}

	// Es el mismo método que el de arriba, el problema es el contexto, la
	// navegación cambia, se complica. Por eso meti este sobrecargado. Por más que
	// use un contexto general cambiaria la navegación, si usara un solo metodo.
	public static void addModifiersClassDeclaration(CommonComponent commonComponent,
											JavaParser.ClassDeclarationContext ctx) {

				int tam = ctx.getParent().children.size();
		if (tam == 1) {
		commonComponent.setAccessModifier("");
		} else if (tam > 1) {
			for (int i = 0; i < tam - 1; i++) {
				String modifier = ctx.getParent().getChild(i).getText();

				if (!modifier.contains("@") && !modifier.contains("final") && !modifier.contains("static") && !modifier.contains("abstract")) {
				commonComponent.setAccessModifier(verifyAccess(modifier));
					// System.out.println("Modificador de acceso componente: "+
					// commonComponent.getAccesModifier());
					if (!commonComponent.getAccessModifier().equals("")) {
						continue;
					}

				}
				if(!modifier.contains("@")){
					commonComponent.getModifiersList().add(modifier);

				}
			}

		}
	}

	public static void addModifierInterfaceDeclaration(CommonComponent commonComponent,
											 JavaParser.InterfaceDeclarationContext ctx) {
		int tam = ctx.getParent().children.size();
		if (tam ==2) {
			commonComponent.setAccessModifier("public");
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
