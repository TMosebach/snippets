package de.tmosebach.codegenerator.metamodel;

public class DataType extends Type {

    public DataType(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
