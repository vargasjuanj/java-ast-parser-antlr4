package com.antrl.recognizer.java.parseUnit.member;

import org.antlr.v4.runtime.misc.Interval;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.CommonType;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Annotation extends CommonType {

	
	
	public void addData(JavaParser.AnnotationContext ctx) {
		System.out.println("INICIO ANOTACIÓN");

		System.out.println(ctx.depth()); //Profundidad en el arbol donde está la anotación, las que etan fuera de la clase son prof 4, de atributos 8, anotación dentro de otra es 12

		System.out.println(ctx.getText());
		System.out.println(ctx.getPayload());
		System.out.println(ctx.getRuleContext());
		System.out.println(ctx.toString());
		
		
		int begin_offset = ctx.start.getStartIndex();
        int end_offset = ctx.stop.getStopIndex();
		System.out.println( begin_offset+"  "+end_offset );

		Interval interval = new Interval(begin_offset, end_offset);
		System.out.println( interval );

//De tal intervalo extrae cierto texto
		String methodText = ctx.start.getInputStream().getText(interval);
		
        System.out.println( methodText );
		System.out.println("FIN ANOTACIÓN");


//		
//		addModifiersMemberDeclaration(this, ctx);
//		name = ctx.fieldDeclaration().variableDeclarators().variableDeclarator(0).variableDeclaratorId().getText();
//		// System.out.println("nombre "+name);
//		if (name.contains("[]")) { // La gramatica tiene una pequeña deficiencia, si los corchetes estan a la
//									// derecha del nombre, los toma como parte del nombre del atributo.
//			name = name.replace("[]", "");
//			type = ctx.fieldDeclaration().typeType().getText(); // Extraigo toda la data del nodo typeType, puede
//																// agarrar tanto una lista, array , tipo simple, el tipo
//																// generico o clases parametrizadas dentro de una lista,
//																// ej. List<Algo<String,Object>>;
//			type += "[]"; // formo el tipo completo
//		} else {
//			type = ctx.fieldDeclaration().typeType().getText();
//		}
		// System.out.println(name);
		// System.out.println(type);

	}
}
