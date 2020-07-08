package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.CommonComponent;

import lombok.Data;
import lombok.ToString;


@Data
//@ToString(callSuper = true)
public class Attribute extends CommonComponent {
	private String structure="";
	private String typeRelation="";

	public void addData(JavaParser.MemberDeclarationContext ctx) {

		name = ctx.fieldDeclaration().variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();

		if (name.contains("[]")) { // La gramatica tiene una pequeña deficiencia, si los corchetes estan a la
									// derecha del nombre, los toma como parte del nombre del atributo.
			structure="Array";
			name = name.replace("[]", "");
			type = ctx.fieldDeclaration().typeType().getText(); // Extraigo toda la data del nodo typeType, puede

																// generico o clases parametrizadas dentro de una lista// ej. List<Algo<String,Object>>;
			//type += "[]"; // formo el tipo completo

		} else {

			type = ctx.fieldDeclaration().typeType().getText();
			if(type.contains("[]"))	{
				type=type.replace("[]","");
				// agarrar tanto una lista, array , tipo simple, el tipo
				structure="Array";
			}
			else if(type.startsWith("List<")){
				type=type.replaceFirst("List<","").replaceFirst(">","");
				structure="List";
			}

		}

		addModifiersMemberDeclaration(this, ctx);
	}
	public void selectTypeRelation(String nameAnnotation) {
		if(nameAnnotation.startsWith("OneToOne")){
			typeRelation="11";
		}else if(nameAnnotation.startsWith("OneToMany")){
			typeRelation="1N";
		}else if(nameAnnotation.startsWith("ManyToOne")){
			typeRelation="N1";
		}else if(nameAnnotation.startsWith("ManyToMany")){
			typeRelation="NN";
		}
	}
	@Override
	public String toString() {
		return "Attribute{" +
				"typeRelation='" + typeRelation + '\'' +
				", accessModifier='" + accessModifier + '\'' +
				", modifiersList=" + modifiersList +
				", name='" + name + '\'' +
				", type='" + type + '\'' +

				", annotationsList=" + annotationsList +
				'}';
	}
}
