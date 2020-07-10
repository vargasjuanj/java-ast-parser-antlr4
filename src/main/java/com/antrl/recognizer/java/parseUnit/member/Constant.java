package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;
import lombok.Data;
import lombok.ToString;
import org.antlr.v4.runtime.tree.TerminalNode;

@Data
@ToString
public class Constant {
    private String type="";
    private String name="";
    private Object value;
    private String structure="";

    public void addData(JavaParser.ConstantDeclaratorContext ctx) {
    type=ctx.getParent().getChild(0).getText();
    name=ctx.IDENTIFIER().getText();
    value=ctx.variableInitializer().getText();
    selectStructure(ctx);
    }

    private void selectStructure(JavaParser.ConstantDeclaratorContext ctx) {
        if(ctx.getChildCount()>3){
            structure="Array";
            type=type.replace("[]","");
        }else if(type.startsWith("List<")){
            structure="List";
            type=type.replaceFirst("List<","") .replaceFirst(">","");

        }else if(type.contains("[]")){
            structure="Array";
            type=type.replace("[]","");
        }
    }
}
