package com.antrl.recognizer.java.parseUnit.member;

import java.util.ArrayList;
import java.util.List;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.CommonComponent;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class CommonConstructorMethod extends CommonComponent {

	protected List<FormalParameter> formalParametersList = new ArrayList();


	public void addFormalParameters(JavaParser.FormalParametersContext ctx) { // Contexto en comun de un metodo o
																				// constructor
		FormalParameter formalParameter = null;
		int tam = 0;
		try {
			tam = ctx.formalParameterList().formalParameter().size(); // Entre los hijos se encuentra la coma tambien
																		// (token COMMA)
		} catch (Exception e) {

		}
		for (int i = 0; i < tam; i++) {
			formalParameter = new FormalParameter();
			formalParameter.setType(ctx.formalParameterList().formalParameter(i).typeType().getText());
			formalParameter.setName(ctx.formalParameterList().formalParameter(i).variableDeclaratorId().getText());
			// System.out.println("Nombre parametro Metodo: "+formalParameter.getName());
			// System.out.println("Nombre tipo parametro de Metodo:
			// "+formalParameter.getType());
			formalParametersList.add(formalParameter);

		}
	}

	public abstract void addData(JavaParser.MemberDeclarationContext ctx);

}
