package com.antrl.recognizer.java.parseUnit;

import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.parseUnit.member.Annotation;
import com.antrl.recognizer.java.parseUnit.member.Attribute;
import com.antrl.recognizer.java.parseUnit.member.ElementValuePair;
import com.antrl.recognizer.java.parseUnit.member.Method;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public abstract class CommonType extends CommonComponent {
protected String _package;

  protected List<String> importsList = new ArrayList<>();
    protected List<Attribute> attributesList = new ArrayList<>();
  protected List<Method> methodsList = new ArrayList<>();
protected List<Annotation> externalAnnotationsList= new ArrayList();

   protected List<String> typeParametersList = new ArrayList<>(); // Parametrización de la clase, si es q lo es.
    public static Annotation addAnnotation(JavaParser.ClassOrInterfaceModifierContext ctx) {
            Annotation annotation= new Annotation();
            annotation.setName(ctx.annotation().qualifiedName().getText()); // @FunctionalInterface

            if(ctx.annotation().elementValuePairs()!=null){
                //System.out.println("ANOTACION ELEMENTVALUEPAIR");
                // Para                // @Column(name="pablo",apellido="picapiedra")
                for (int i=0; i<ctx.annotation().elementValuePairs().elementValuePair().size(); i++){
                    ElementValuePair elementValuePair= new ElementValuePair();
                    elementValuePair.setName(ctx.annotation().elementValuePairs().elementValuePair(i).IDENTIFIER().getText());
                    elementValuePair.setValue(ctx.annotation().elementValuePairs().elementValuePair(i).elementValue().getText());
                    annotation.getElementValuePairs().add(elementValuePair);

                    //un valor representado como anotacion, key , value=annotation
    /*
                    if(ctx.annotation().elementValuePairs().elementValuePair(i).elementValue().annotation()!=null){
        ElementValuePair elementValuePair2= new ElementValuePair();
        annotation.getElementValuePairs(i)
    }
*/
                }

                return annotation;
            }else if(ctx.annotation().elementValue()!=null){
                //Para                // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
                //System.out.println("ANOTACION ARRAYiNITIALIZER");
                for (int i=0; i<ctx.annotation().elementValue().elementValueArrayInitializer().elementValue().size(); i++){
                    annotation.getElementValueArrayInitializer().add(ctx.annotation().elementValue().elementValueArrayInitializer().elementValue(i).getText());

                }


return annotation;
            }

return annotation;

    }

    public void addAttribute(Attribute attribute) {
            attributesList.add(attribute);
        System.out.println(attribute.toString());


    }
    public void addMethod(Method method) {
        System.out.println(method.toString());
        methodsList.add(method);
    }
    public void addImport(String _import) {
        importsList.add(_import);
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
