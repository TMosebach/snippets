package de.tmosebach.codegenerator.translater;

import de.tmosebach.codegenerator.metamodel.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Translator {

    private STGroup group = new STGroupFile("./src/main/resources/templates/Class.stg");


    private static Model createModel() {
        DataType stringType = new DataType("String");

        Entity post = new Entity("Post");
        //post.setSuperType(new Entity("HasAuthor"));
        post.addAttribut(new Attribut("title", stringType));
        post.addAttribut(new Attribut("content", stringType));
        post.addFeature(new Feature("comments", new Entity("Comment")));

        Model model = new Model();
        model.setEntities(Arrays.asList(post));
        return model;
    }

    public static void main(String[] argv) {
        Model model = createModel();

        Translator translator = new Translator();
        translator.tranlate(model);
    }

    public void tranlate(Model model) {
        model.getEntities().forEach(e -> tranlate(e));
    }

    private void tranlate(Entity entity) {
        try {
            File ausgabeVerzeichnis = new File("./target/generated-sources/model/");
            if(!ausgabeVerzeichnis.exists()) {
                ausgabeVerzeichnis.mkdirs();
            }

            PrintWriter writer =
                    new PrintWriter(new File(ausgabeVerzeichnis, entity.getCapitalizeName() + ".java"));

            ST classTemplate = group.getInstanceOf("class");
            classTemplate.add("c", entity);
            //System.out.println(classTemplate.render());
            writer.append(classTemplate.render());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
