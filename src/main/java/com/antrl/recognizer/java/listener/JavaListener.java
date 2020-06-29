package com.antrl.recognizer.java.listener;


import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.JavaParserBaseListener;
import com.antrl.recognizer.java.parseUnit.ClassDefinition;
import com.antrl.recognizer.java.parseUnit.member.Annotation;
import com.antrl.recognizer.java.parseUnit.member.Attribute;
import com.antrl.recognizer.java.parseUnit.member.Constructor;
import com.antrl.recognizer.java.parseUnit.member.Method;

public class JavaListener extends JavaParserBaseListener {
// Usar logs
	/*
	 * Nota: Para los nodos que son opcionales como las implementaciones y tipos
	 * parametrizados de la clase, añadir un try catch en el metodo que lo analice,
	 * ya que se crean solo si existen, sino salta el null pointer exception.
	 * 
	 * A LA CLASEORINTERFACE DIVIDIRLA EN DOS EN CLASE Y EN INTERFACE, QUE ENTIENDAN
	 * DE ALGUNA, VER Q ONDA, PUEDEN EXTENDER DE UNA QUE YA EXTIENDA DE
	 * COMMONCOMPONENT
	 */

	private int countClassess; // falta
	private int countInterfaces; // pendiente
	private int countEnums; // pendiente
	private ClassDefinition _class = new ClassDefinition();

	@Override
	public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
		System.out.println("Nombre del packete: " + ctx.qualifiedName().getChild(0).getText()); // Obtiene el primer
																								// hijo que es
																								// obligatorio
																								// (data_test), de una
																								// lista (que se produce
																								// cuando lleva *, que
																								// puede ser null, o +
																								// que si o si uno hay)
																								// porque un token no
																								// puede estar vacio,
																								// esa condición se pone
																								// en el parser
		_class.set_package(ctx.qualifiedName().getText()); // Obtiene e texto de todos los hijos concatenados, el punto
															// es DOT en el lexer, es el que separa IDENTIFIERS.
															// QuualifierName es una producción compuesta por simbolos
															// terminales.
	}

	@Override
	public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
		System.out.println("Importación: " + ctx.qualifiedName().getText());
		_class.addImport(ctx.qualifiedName().getText());
	}

	@Override // para clases internas tmb
	public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
		// si es mayor a uno significa que tiene alguna/s clase/s interna/s
		countClassess++; // falta el tema de subclass
		_class.addData(ctx);

	}

	@Override // interfaces internas tmb
	public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
		countInterfaces++;
	}

	@Override // enums internas tambien
	public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
		countEnums++;
	}

	@Override // ?
	public void enterAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		
		System.out.println(ctxMemberDeclaration);
	}

	public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
		JavaParser.MemberDeclarationContext ctxMemberDeclaration = (JavaParser.MemberDeclarationContext) ctx
				.getParent(); // Obtengo el contexto del padre
		Attribute attribute = new Attribute();
		attribute.addData(ctxMemberDeclaration);
		_class.addAttribute(attribute);
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
		_class.addMethod(method);
		method.addData(ctxMemberDeclaration);

	}
	
	

	@Override
	public void enterAnnotation(JavaParser.AnnotationContext  ctx) {
		// TODO Auto-generated method stub
		super.enterAnnotation(ctx);
		
		Annotation anotation=new Annotation();
		anotation.addData(ctx);
		_class.addAnnotation(anotation);
	}

	@Override
	public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
		_class.totalize();
	}

	/*
	 * String _classModifier, name = "", modifier = "", modifierAux = "",
	 * modifierAux2 = "", type = "", params = ""; int separator, modifierCount = 0;
	 * List<JavaParser.FormalParameterContext> parametersList;
	 * 
	 * @Override public void enterTypeDeclaration(JavaParser.TypeDeclarationContext
	 * ctx) { for (int i = 0; i < ctx._classModifier().size(); i++) { _classModifier
	 * += ctx._classModifier().get(i).getText() + " ";
	 * 
	 * }
	 * 
	 * System.out.println(
	 * "-------------------------------------------------------------------"); if
	 * (_classModifier.contains("abstract")) {
	 * System.out.println("                         <<abstract>>"); } try { if
	 * (ctx.interfaceDeclaration().getText() != null) {
	 * System.out.println("                         <<interface>>");
	 * 
	 * System.out.println("                                           " +
	 * ctx.interfaceDeclaration().IDENTIFIER().getText()); System.out.println(
	 * "-------------------------------------------------------------------");
	 * return; } } catch (Exception e) { }
	 * 
	 * System.out.println("                            " +
	 * ctx.classDeclaration().IDENTIFIER().getText());
	 * 
	 * System.out.println(
	 * "-------------------------------------------------------------------");
	 * 
	 * }
	 * 
	 * public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
	 * name =
	 * ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().
	 * getText(); loadModifiers(ctx.parent.parent); //a través del padre llego a los
	 * hermanos
	 * 
	 * modifierAnalysis(); type =ctx.getChild(0).getText(); //equals ->
	 * ctx.typeType()._classType().IDENTIFIER(0)
	 * 
	 * if (name.contains("[]")) { name = name.replace("[]", ""); type += "[]"; }
	 * System.out.println(modifier + " " + name + " " + ": "+type);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override public void
	 * enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
	 * 
	 * separar(); type = ""; methodAnalysis(ctx.IDENTIFIER(),
	 * ctx.formalParameters(), ctx.parent.parent);
	 * 
	 * 
	 * }
	 * 
	 * private void separar() { if (separator == 0) { System.out.println(
	 * "-------------------------------------------------------------------");
	 * separator++; } }
	 * 
	 * public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
	 * 
	 * type = ctx.getChild(0).getText(); methodAnalysis(ctx.IDENTIFIER(),
	 * ctx.formalParameters(), ctx.parent.parent);
	 * 
	 * }
	 * 
	 * private void methodAnalysis(TerminalNode id,
	 * JavaParser.FormalParametersContext formalParameters, RuleContext rule) {
	 * 
	 * name = id.getText(); loadModifiers(rule); modifierAnalysis();
	 * 
	 * try { parametersList =
	 * formalParameters.formalParameterList().formalParameter(); } catch (Exception
	 * e) { } parameterAnalysis(); System.out.println(modifier + " " + name + params
	 * + ":" + type);
	 * 
	 * 
	 * }
	 * 
	 * public void loadModifiers(RuleContext rule) { modifier = ""; modifierAux =
	 * rule.getChild(0).getText(); modifierAux2 = rule.getText(); modifierCount =
	 * rule.getChildCount();
	 * 
	 * }
	 * 
	 * public void modifierAnalysis() { if (modifierCount == 2) {
	 * 
	 * modifier = modifierAux; if (modifier.equals("private")) modifier = "-"; else
	 * if (modifier.equals("public")) modifier = "+"; else if
	 * (modifier.equals("protected")) modifier = "#"; else if
	 * (modifier.equals("final")) { modifier = ""; name = name.toUpperCase(); } else
	 * { modifier = ""; name = name + " _static"; }
	 * 
	 * } else if (modifierCount > 2) {
	 * 
	 * modifier = modifierAux2;
	 * 
	 * if (modifier.contains("final")) { name = name.toUpperCase();
	 * 
	 * } if (modifier.contains("static")) { name = name + " _static";
	 * 
	 * } if (modifier.contains("private")) modifier = "-"; else if
	 * (modifier.contains("public")) modifier = "+"; else if
	 * (modifier.contains("protected")) modifier = "#"; else { modifier = ""; }
	 * 
	 * } }
	 * 
	 * public void parameterAnalysis() { String paramName = "", paramType = ""; int
	 * paramsCount = 0; try { paramsCount = parametersList.size(); } catch
	 * (Exception e) { paramsCount = 0; params = "()"; }
	 * 
	 * if (paramsCount != 0) { params = "(";
	 * 
	 * for (int i = 0; i < paramsCount; i++) { int paramChildren =
	 * parametersList.get(i).getChildCount(); if (paramChildren == 3) { //
	 * paramModifier=ctx.formalParameters().formalParameterList().formalParameter(i)
	 * .getChild(0).getText(); // params+=paramModifier;
	 * 
	 * paramName = " " + parametersList.get(i).getChild(2).getText(); paramName =
	 * paramName.toUpperCase(); params += paramName + " ";
	 * 
	 * paramType = parametersList.get(i).getChild(1).getText(); params += ":" +
	 * paramType + ",";
	 * 
	 * } else if (paramChildren == 2) { paramName = " " +
	 * parametersList.get(i).getChild(1).getText(); params += paramName + " ";
	 * paramType = parametersList.get(i).getChild(0).getText(); params += ":" +
	 * paramType + ",";
	 * 
	 * }
	 * 
	 * } StringBuilder aux = new StringBuilder(params); params = aux.substring(0,
	 * params.length() - 1); params += " )"; } parametersList=null; }
	 * 
	 * public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
	 * System.out.println(
	 * "-------------------------------------------------------------------");
	 * 
	 * }
	 * 
	 */
}