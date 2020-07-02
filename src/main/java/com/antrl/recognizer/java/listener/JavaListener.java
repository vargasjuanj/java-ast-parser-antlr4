package com.antrl.recognizer.java.listener;


import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.JavaParserBaseListener;
import com.antrl.recognizer.java.parseUnit.ClassDefinition;
import com.antrl.recognizer.java.parseUnit.InterfaceDefinition;
import com.antrl.recognizer.java.parseUnit.member.*;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
public class JavaListener extends JavaParserBaseListener {
	// Usar logs

	//Ver lo de atributos en null y fijarse que lo demas funcione bien
	/*
	 * Nota: Para los nodos que son opcionales como las implementaciones y tipos
	 * parametrizados de la clase, añadir un try catch en el metodo que lo analice,
	 * ya que se crean solo si existen, sino salta el null pointer exception.
	 *

	 *     @Override
    public void exitLinea(GestrategiacsvParser.LineaContext ctx) { //linea cargada
        if(ctx.getParent().getRuleIndex()==GestrategiacsvParser.RULE_cabecera){
            return;
        }
        }
        *
        *
        *
        *
        * otro tipo de anotacion
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
String juan;
	 /
	 */
	private boolean isClass;
	private boolean isInterface;
	private ClassDefinition _class = new ClassDefinition();
	private InterfaceDefinition _interface= new InterfaceDefinition();
	private List<Annotation> externalAnnotationsList= new ArrayList();



	@Override
	public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
		System.out.println("Importación: " + ctx.qualifiedName().getText());
		_class.addImport(ctx.qualifiedName().getText());
		_interface.addImport(ctx.qualifiedName().getText());
	}



	public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		Attribute attribute = new Attribute();
		attribute.addData(ctxMemberDeclaration);

		if(isClass){
			_class.addAttribute(attribute);
		}else if(isInterface){
			_class.addAttribute(attribute);
		}

	}

	@Override
	public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		Constructor constructor = new Constructor();
		constructor.addData(ctxMemberDeclaration);
		_class.addConstructor(constructor);

	}

	@Override
	public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		Method method = new Method();
		method.addData(ctxMemberDeclaration);
		if(isClass){
			_class.addMethod(method);

		}else if(isInterface){
			_interface.addMethod(method);
		}

	}


	@Override
	public void enterAnnotation(JavaParser.AnnotationContext ctx) {
		// TODO Auto-generated method stub
		super.enterAnnotation(ctx);

		Annotation anotation = new Annotation();
		anotation.addData(ctx);
		if(isClass){
			_class.addAnnotation(anotation);

		}else if(isInterface){
			_interface.addAnnotation(anotation);
		}
	}

	@Override
	public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
		super.enterClassOrInterfaceModifier(ctx);
		if(ctx.annotation()!=null){
			Annotation annotation= new Annotation();
			annotation.setName(ctx.annotation().qualifiedName().getText());

			if(ctx.annotation().elementValuePairs()!=null){
				System.out.println("ANOTACION ELEMENTVALUEPAIR");
				for (int i=0; i<ctx.annotation().elementValuePairs().elementValuePair().size(); i++){
					ElementValuePair elementValuePair= new ElementValuePair();
					elementValuePair.setName(ctx.annotation().elementValuePairs().elementValuePair(i).IDENTIFIER().getText());
					elementValuePair.setValue(ctx.annotation().elementValuePairs().elementValuePair(i).elementValue().getText());
					annotation.getElementValuePairs().add(elementValuePair);
					externalAnnotationsList.add(annotation);
				}

			}else if(ctx.annotation().elementValue()!=null){
				System.out.println("ANOTACION ARRAYiNITIALIZER");
				for (int i=0; i<ctx.annotation().elementValue().elementValueArrayInitializer().elementValue().size(); i++){
					annotation.getElementValueArrayInitializer().add(ctx.annotation().elementValue().elementValueArrayInitializer().elementValue(i).getText());

				}

			}
			System.out.println(annotation.toString());
		}
	}

	@Override
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
		super.enterClassDeclaration(ctx);
		isClass=true;
		_interface.setImportsList(new ArrayList());  //lo seteo porque esto lo comparten tmb
		_class.setExternalAnnotationsList(externalAnnotationsList);
		_class.addData(ctx);
	}


	@Override
	public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
		super.enterInterfaceDeclaration(ctx);
		isInterface=true;
		_class.setImportsList(new ArrayList());
		_interface.setExternalAnnotationsList(externalAnnotationsList);
		_interface.addData(ctx);
	}

	@Override
	public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
		if(isClass){
			_class.totalize();
		}else if(isInterface){
			_interface.totalize();
		}

	}
}
