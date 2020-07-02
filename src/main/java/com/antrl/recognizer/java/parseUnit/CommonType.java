package com.antrl.recognizer.java.parseUnit;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.member.Annotation;
import com.antrl.recognizer.java.parseUnit.member.Attribute;
import com.antrl.recognizer.java.parseUnit.member.Method;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class CommonType extends CommonComponent {
protected String _package;
  protected List<String> importsList = new ArrayList<>();
   protected List<Annotation> annotationsList = new ArrayList<>();
    protected List<Attribute> attributesList = new ArrayList<>();
  protected List<Method> methodsList = new ArrayList<>();
protected List<Annotation> externalAnnotationsList= new ArrayList();

   protected List<String> typeParametersList = new ArrayList<>(); // Parametrización de la clase, si es q lo es.

    public void addAttribute(Attribute attribute) {
        System.out.println(attribute.toString());
        attributesList.add(attribute);
    }
    public void addMethod(Method method) {
        System.out.println(method.toString());
        methodsList.add(method);
    }
    public void addImport(String _import) {
        importsList.add(_import);
    }
    public void addAnnotation(Annotation annotation) {
        //System.out.println(annotation);
        annotationsList.add(annotation);
    }
    public void addTypeParametersClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
       try{
           addTypeParameters(ctx.typeParameters().typeParameter());

       }catch(Exception e){
           System.out.println("no hay parametrización");
       }

    }
    public void addTypeParametersInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        try{
            addTypeParameters(ctx.typeParameters().typeParameter());

        }catch(Exception e){
            System.out.println("no hay parametrización");
        }

    }

public void addTypeParameters(List<JavaParser.TypeParameterContext> typeParameterContexts){


        int tam = typeParameterContexts.size();
        for (int i = 0; i < tam; i++) {
            String parameter =  typeParameterContexts.get(i).IDENTIFIER().getText();
            System.out.println("Tiene un parametro de tipo: " + parameter);
            typeParametersList.add(parameter);
        }




}
    abstract void totalize();

}
