package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.parseUnit.CommonComponent;
import com.antrl.recognizer.java.parseUnit.CommonType;
import org.antlr.v4.runtime.misc.Interval;

import com.antrl.recognizer.java.JavaParser;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class Annotation  {
private String name;
	List<ElementValuePair> elementValuePairs= new ArrayList();
	List<String> elementValueArrayInitializer= new ArrayList();
	public Annotation addData(JavaParser.AnnotationContext ctx) {
		return CommonType.addAnnotation((JavaParser.ClassOrInterfaceModifierContext) ctx.getParent());
		//System.out.println("INICIO ANOTACIÓN");

		//System.out.println(ctx.depth()); //Profundidad en el arbol donde está la anotación, las que etan fuera de la clase son prof 4, de atributos 8, anotación dentro de otra es 12

		//System.out.println(ctx.getText());
		//System.out.println(ctx.getPayload());
		//System.out.println(ctx.getRuleContext());
		//System.out.println(ctx.toString());
		
		
		//int begin_offset = ctx.start.getStartIndex();
        //int end_offset = ctx.stop.getStopIndex();
		//System.out.println( begin_offset+"  "+end_offset );

		//Interval interval = new Interval(begin_offset, end_offset);
		//System.out.println( interval );

//De tal intervalo extrae cierto texto
		//String methodText = ctx.start.getInputStream().getText(interval);
	}

	@Override
	public String toString() {
		return "Annotation{" +
				"name='" + name + '\'' +
				", elementValuePairs=" + elementValuePairs +
				", elementValueArrayInitializer=" + elementValueArrayInitializer +
				'}';
	}
}
