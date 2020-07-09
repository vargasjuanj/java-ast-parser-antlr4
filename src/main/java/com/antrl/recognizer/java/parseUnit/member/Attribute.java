package com.antrl.recognizer.java.parseUnit.member;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.CommonComponent;

import lombok.Data;
import lombok.ToString;


@Data
//@ToString(callSuper = true)
public class Attribute extends CommonComponent {
	private String structure="";
	private String typeRelation="";
    private Object value="";
	public void addData(JavaParser.VariableDeclaratorContext ctx) {

		name = ctx.variableDeclaratorId().getText();
		try{
            value=ctx.variableInitializer().getText();
        }catch (Exception e){

        }

		if (name.contains("[]")) { // La gramatica tiene una pequeña deficiencia, si los corchetes estan a la
									// derecha del nombre, los toma como parte del nombre del atributo.
			structure="Array";
			name = name.replace("[]", "");
			type = ctx.getParent().getParent().getChild(0).getText();


			//type += "[]"; // formo el tipo completo

		} else {

			type = ctx.getParent().getParent().getChild(0).getText();
			if(type.contains("[]"))	{
				type=type.replace("[]","");
				// agarrar tanto una lista, array , tipo simple, el tipo
				structure="Array";
			}
			else if(type.startsWith("List<")){
				type=type.replaceFirst("List<","").replaceFirst(">","");
				structure="List";
			}

		}

		//Lo casteo al MemberDeclarationContext, porque se reutiliza un metodo apra atributos, metodos y constructores de clase.
		addModifiersMemberDeclaration(this, (JavaParser.MemberDeclarationContext) ctx.getParent().getParent().getParent());
	}
	public void selectTypeRelation(String nameAnnotation) {
		if(nameAnnotation.startsWith("OneToOne")){
			typeRelation="11";
		}else if(nameAnnotation.startsWith("OneToMany")){
			typeRelation="1N";
		}else if(nameAnnotation.startsWith("ManyToOne")){
			typeRelation="N1";
		}else if(nameAnnotation.startsWith("ManyToMany")){
			typeRelation="NN";
		}
	}
	@Override
	public String toString() {
		return "Attribute{" +
				"typeRelation='" + typeRelation + '\'' +
				", accessModifier='" + accessModifier + '\'' +
				", modifiersList=" + modifiersList +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
                ", value='" + value + '\'' +

				", annotationsList=" + annotationsList +
				'}';
	}
}
