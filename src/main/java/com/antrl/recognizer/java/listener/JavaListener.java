package com.antrl.recognizer.java.listener;


import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.JavaParserBaseListener;
import com.antrl.recognizer.java.parseUnit.ClassDefinition;
import com.antrl.recognizer.java.parseUnit.CommonType;
import com.antrl.recognizer.java.parseUnit.InterfaceDefinition;
import com.antrl.recognizer.java.parseUnit.member.*;
import lombok.extern.java.Log;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.List;

@Log
public class JavaListener extends JavaParserBaseListener {
	// Usar logs

	// null en atributos,etc

	/*
	 * Nota: Para los nodos que son opcionales como las implementaciones y tipos
	 * parametrizados de la clase, añadir un try catch en el metodo que lo analice,
	 * ya que se crean solo si existen, sino salta el null pointer exception.
	 *
---ejemplo uso del numero de regla
	 *     @Override
    public void exitLinea(GestrategiacsvParser.LineaContext ctx) { //linea cargada
        if(ctx.getParent().getRuleIndex()==GestrategiacsvParser.RULE_cabecera){
            return;
        }
        }

---ejemplo casteo a cierto contexto a traves de la navegación. De MemberDeclarationContext a su anotacion modifier
		JavaParser.AnnotationContext annota= (JavaParser.AnnotationContext) ctx.getParent().getChild(0).getChild(0).getChild(0);

	 */
	private boolean isClass;
	private boolean isInterface;
	private ClassDefinition _class = new ClassDefinition();
	private InterfaceDefinition _interface= new InterfaceDefinition();
	private List<Annotation> externalAnnotationsList= new ArrayList<>();
	private List<Annotation> annotationsMemberList= new ArrayList<>();
	private String typeRelationAux="";


	@Override
	public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
		_class.addImport(ctx.qualifiedName().getText());
		_interface.addImport(ctx.qualifiedName().getText());
	}

	@Override
	public void enterAnnotation(JavaParser.AnnotationContext ctx) {
		// TODO Auto-generated method stub
		super.enterAnnotation(ctx);
		//anotaciones internas para un metodo o atributo
		if (ctx.getParent().getParent().getParent().getRuleIndex() == JavaParser.RULE_classBodyDeclaration) {
			annotationsMemberList.add(CommonType.addAnnotation((JavaParser.ClassOrInterfaceModifierContext) ctx.getParent()));
			String nameAnnotation=ctx.qualifiedName().getText();
			if(nameAnnotation.startsWith("OneTo") || nameAnnotation.startsWith("ManyTo")){
				typeRelationAux=nameAnnotation;
			}


		}else if(ctx.getParent().getParent().getRuleIndex()==JavaParser.RULE_typeDeclaration){  //Anotaciones externas al tipo clase o interface
			externalAnnotationsList.add(CommonType.addAnnotation((JavaParser.ClassOrInterfaceModifierContext) ctx.getParent()));
		}else{
			//para anotación dentro de otra, dentro de un value de un elementValuePair. Queda como value asi como esta
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
	public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		Attribute attribute = new Attribute();
		attribute.addData(ctxMemberDeclaration);
		attribute.setAnnotationsList(annotationsMemberList);
		annotationsMemberList= new ArrayList<>();// Se vuelve a inicializar para otro miembro
			attribute.selectTypeRelation(typeRelationAux);
 			typeRelationAux="";

		if(isClass){
			_class.addAttribute(attribute);
		}else if(isInterface){
			_interface.addAttribute(attribute);
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
		method.setAnnotationsList(annotationsMemberList);
		annotationsMemberList= new ArrayList<>();
		if(isClass){
			_class.addMethod(method);

		}else if(isInterface){
			_interface.addMethod(method);
		}

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
