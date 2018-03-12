package de.tmosebach.codegenerator.metamodel;

public class Feature {
    private String name;
    private Type type;

    public Feature(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getCapitalizedName() {
        String capitalizeName =
                name.substring(0,1).toUpperCase()
                        + name.substring(1);
        return capitalizeName;
    }

    public Type getType() {
        return type;
    }
}
