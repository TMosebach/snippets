package de.tmosebach.codegenerator;

import de.tmosebach.codegenerator.parser.ASTGenerator;
import de.tmosebach.codegenerator.metamodel.Model;
import de.tmosebach.codegenerator.model.DomainLexer;
import de.tmosebach.codegenerator.model.DomainParser;
import de.tmosebach.codegenerator.translater.Translator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;

public class CodeGenerator {
    public static void main(String[] argv) throws IOException {

        System.out.println(new File(".").getAbsoluteFile());

        DomainLexer lexer = new DomainLexer(CharStreams.fromFileName("./src/test/resources/Description.domain"));
        DomainParser parser = new DomainParser(new CommonTokenStream(lexer));

        ASTGenerator astGenerator = new ASTGenerator();
        astGenerator.visit(parser.model());

        Model model = astGenerator.getModel();
        Translator tansTranslator = new Translator();
        tansTranslator.tranlate(model);
    }
}
