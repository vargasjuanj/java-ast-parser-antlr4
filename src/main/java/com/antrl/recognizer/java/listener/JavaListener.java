package com.antrl.recognizer.java.listener;


import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.JavaParserBaseListener;
import com.antrl.recognizer.java.parseUnit.ClassDefinition;
import com.antrl.recognizer.java.parseUnit.CommonType;
import com.antrl.recognizer.java.parseUnit.InterfaceDefinition;
import com.antrl.recognizer.java.parseUnit.member.*;
import lombok.extern.java.Log;

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
	private boolean isEntity;
	private boolean isAbstract;
	private boolean isFinal;
	private boolean isFunctional; //para interface funcional
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
		String nameAnnotation=ctx.qualifiedName().getText();

		//anotaciones internas para un metodo o atributo
		if (ctx.getParent().getParent().getParent().getRuleIndex() == JavaParser.RULE_classBodyDeclaration) {
			annotationsMemberList.add(CommonType.addAnnotation((JavaParser.ClassOrInterfaceModifierContext) ctx.getParent()));
			if(nameAnnotation.startsWith("OneTo") || nameAnnotation.startsWith("ManyTo")){
				typeRelationAux=nameAnnotation;
			}


		}else if(ctx.getParent().getParent().getRuleIndex()==JavaParser.RULE_typeDeclaration){  //Anotaciones externas al tipo clase o interface
			if(nameAnnotation.startsWith("Entity")){
				isEntity=true;
			}else if(nameAnnotation.startsWith("FunctionalInterface")){
				isFunctional=true;
			}
			externalAnnotationsList.add(CommonType.addAnnotation((JavaParser.ClassOrInterfaceModifierContext) ctx.getParent()));
			
		}else{
			//para anotación dentro de otra, dentro de un value de un elementValuePair. Queda como value asi como esta
		}
	}

	@Override
	public void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx) {
		super.enterClassOrInterfaceModifier(ctx);
		if(ctx.getParent().getRuleIndex()==JavaParser.RULE_typeDeclaration) {
			if (ctx.getText().equals("abstract")) {
				System.out.println("aca " + ctx.getText());
				isAbstract = true;
			} else if (ctx.getText().equals("final")) {
				isFinal = true;
			}
		}
	}

	@Override
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
		super.enterClassDeclaration(ctx);
	//No se evita que el listener recorra el arbol, pero se evitan las operaciones de extraccion de datos que se hacen en los nodos con los atributos booleanos isClass, isInterface e isEntity
		isClass = true;
		if(isEntity){
			_class.setExternalAnnotationsList(externalAnnotationsList);
			_interface.setImportsList(new ArrayList());  //lo seteo porque esto lo comparten tmb
			_interface.setExternalAnnotationsList(new ArrayList<>());
			_class.addData(ctx);
			_class.setEntity(isEntity);
			_class.setFinal(isFinal);
		}else if(isAbstract){
		_class.setAbstract(true);
		}else{
			_class.setExternalAnnotationsList(new ArrayList<>());
		}

	}

	@Override
	public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
		super.enterVariableDeclarator(ctx);
		//JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
			//	.getParent().getParent().getParent();

		Attribute attribute = new Attribute();
		attribute.addData(ctx);
		attribute.setAnnotationsList(annotationsMemberList);
		annotationsMemberList= new ArrayList<>();// Se vuelve a inicializar para otro miembro
		attribute.selectTypeRelation(typeRelationAux);
		typeRelationAux="";

		if(isClass){
			if(isEntity){
				_class.addAttribute(attribute);
			}else if(isAbstract){

			}

		}else if(isInterface){
			_interface.addAttribute(attribute);
		}
	}
/*
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
			if(isEntity){
				_class.addAttribute(attribute);
			}else if(isAbstract){

			}

		}else if(isInterface){
			_interface.addAttribute(attribute);
		}

	}
*/
	@Override
	public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		Constructor constructor = new Constructor();
		constructor.addData(ctxMemberDeclaration);
		//if(isEntity || isAbstract){
		if(isClass){
			if(isEntity){
				_class.addConstructor(constructor);

			}else if(isAbstract){

			}
		}

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
			if(isEntity){
				_class.addMethod(method);
			}else if(isAbstract){
				//
			}


		}else if(isInterface){
			_interface.addMethod(method);
		}

	}

	@Override
	public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
		super.enterInterfaceDeclaration(ctx);
		isInterface=true;
		_class.setImportsList(new ArrayList());
		_class.setExternalAnnotationsList(new ArrayList<>());
		_interface.setExternalAnnotationsList(externalAnnotationsList);
		_interface.setFunctional(isFunctional);
		_interface.addData(ctx);


	}


	@Override
	public void enterConstantDeclarator(JavaParser.ConstantDeclaratorContext ctx) {
		super.enterConstantDeclarator(ctx);
		Constant constant = new Constant();
		constant.addData(ctx);
		System.out.println(	constant.toString());
		_interface.getConstantsList().add(constant);
	}

	@Override
	public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
		if(isClass){
			if(isEntity){
				_class.totalize();

			}else if(isAbstract){

			}
		}else if(isInterface){
			_interface.totalize();
		}

	}
}
