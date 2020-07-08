package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;

import lombok.Data;
import lombok.ToString;

@Data
//@ToString(callSuper = true)
public class Method extends CommonConstructorMethod {

	@Override
	public void addData(JavaParser.MemberDeclarationContext ctx) {
		type = ctx.methodDeclaration().typeTypeOrVoid().getText();
		addModifiersMemberDeclaration(this, ctx);
		name = ctx.methodDeclaration().IDENTIFIER().getText();
		addFormalParameters(ctx.methodDeclaration().formalParameters());

	}

	@Override
	public String toString() {
		return "Method{" +
				"formalParametersList=" + formalParametersList +
				", oneToOne=" + oneToOne +
				", oneToMany=" + oneToMany +
				", relationWithType='" + relationWithType + '\'' +
				", annotationsList=" + annotationsList +
				", accessModifier='" + accessModifier + '\'' +
				", modifiersList=" + modifiersList +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
