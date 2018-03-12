package de.tmosebach.codegenerator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Entity extends Type {

    private Entity superType;
    private List<Attribut> attributs = new ArrayList<>();
    private List<Feature> features = new ArrayList<>();

    public Entity(String name) {
        super(name);
    }

    public void addAttribut(Attribut attribut) {
        attributs.add(attribut);
    }

    public List<Attribut> getAttributs() {
        return attributs;
    }

    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public Entity getSuperType() {
        return superType;
    }

    public void setSuperType(Entity superType) {
        this.superType = superType;
    }

    public String getCapitalizeName() {
        String capitalizeName =
                getName().substring(0,1).toUpperCase()
                        + getName().substring(1);
        return capitalizeName;
    }
}
