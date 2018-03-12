package de.tmosebach.codegenerator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<DataType> dataTypes = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    public List<DataType> getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(List<DataType> dataTypes) {
        this.dataTypes = dataTypes;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
