package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Constant {
    private String type="";
    private String name="";
    private Object value;

    public void addData(JavaParser.ConstantDeclaratorContext ctx) {
    type=ctx.getParent().getChild(0).getText();
    name=ctx.IDENTIFIER().getText();
    value=ctx.variableInitializer().getText();
    }
}
