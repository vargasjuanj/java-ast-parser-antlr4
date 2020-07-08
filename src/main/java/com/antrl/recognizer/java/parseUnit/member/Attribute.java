package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.CommonComponent;

import lombok.Data;
import lombok.ToString;


@Data
//@ToString(callSuper = true)
public class Attribute extends CommonComponent {
	private String structure;
	public void addData(JavaParser.MemberDeclarationContext ctx) {


		name = ctx.fieldDeclaration().variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
		// System.out.println("nombre "+name);

		if (name.contains("[]")) { // La gramatica tiene una pequeña deficiencia, si los corchetes estan a la
									// derecha del nombre, los toma como parte del nombre del atributo.
			structure="Array";
			name = name.replace("[]", "");
			type = ctx.fieldDeclaration().typeType().getText(); // Extraigo toda la data del nodo typeType, puede

																// generico o clases parametrizadas dentro de una lista,
																// ej. List<Algo<String,Object>>;
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
			}else{
				structure="";
			}

		}
		// System.out.println(name);
		// System.out.println(type);
		addModifiersMemberDeclaration(this, ctx);
	}

	@Override
	public String toString() {
		return "Attribute{" +
				"oneToOne=" + oneToOne +
				", oneToMany=" + oneToMany +
				", structure='" + structure + '\'' +
				", relationWithType='" + relationWithType + '\'' +
				", annotationsList=" + annotationsList +
				", accessModifier='" + accessModifier + '\'' +
				", modifiersList=" + modifiersList +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
