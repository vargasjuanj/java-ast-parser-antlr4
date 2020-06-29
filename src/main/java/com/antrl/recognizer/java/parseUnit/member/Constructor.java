package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Constructor extends CommonConstructorMethod {

	@Override
	public void addData(JavaParser.MemberDeclarationContext ctx) {
		type = "";
		addModifiersMemberDeclaration(this, ctx);
		name = ctx.constructorDeclaration().IDENTIFIER().getText();
		addFormalParameters(ctx.constructorDeclaration().formalParameters());

	}

}
