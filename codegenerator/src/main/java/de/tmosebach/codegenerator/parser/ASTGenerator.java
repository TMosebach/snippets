package de.tmosebach.codegenerator.parser;

import de.tmosebach.codegenerator.metamodel.*;
import de.tmosebach.codegenerator.model.DomainBaseVisitor;
import de.tmosebach.codegenerator.model.DomainParser;

public class ASTGenerator extends DomainBaseVisitor {

    private SymbolTable symbolTable = new SymbolTable();

    private Entity actualEntity;

    @Override
    public Object visitDatatype(DomainParser.DatatypeContext ctx) {
        super.visitDatatype(ctx);

        addDataType(ctx.NAME().getText());
        return null;
    }

    private void addDataType(String typeName) {
        symbolTable.createDataType(typeName);
    }

    @Override
    public Object visitEntity(DomainParser.EntityContext ctx) {
        actualEntity = symbolTable.createEntity(ctx.NAME().getText());

        if (ctx.type_name() != null) {
            String superType = ctx.type_name().getText();
            symbolTable.addSuper(actualEntity, superType);
        }
        Object object = super.visitEntity(ctx);
        return null;
    }

    @Override
    public Object visitAttribut(DomainParser.AttributContext ctx) {
        super.visitAttribut(ctx);

        String name = ctx.NAME().getText();
        String typeName = ctx.type_name().NAME().getText();
        symbolTable.createAttribute(actualEntity, name, typeName);
        return null;
    }

    @Override
    public Object visitFeature(DomainParser.FeatureContext ctx) {
        String name = ctx.NAME().getText();
        String typeName = ctx.type_name().NAME().getText();
        symbolTable.createFeature(actualEntity, name, typeName);
        super.visitFeature(ctx);
        return null;
    }

    @Override
    public Object visitModel(DomainParser.ModelContext ctx) {
        super.visitModel(ctx);
        if(symbolTable.hasOpen()) {
            new RuntimeException("Es gibt nicht deklarierte Typen.");
        }
        return null;
    }

    public Model getModel() {
        Model model = new Model();
        model.setDataTypes(symbolTable.getDataTypes());
        model.setEntities(symbolTable.getEntities());
        return model;
    }
}
