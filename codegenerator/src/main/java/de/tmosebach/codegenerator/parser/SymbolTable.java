package de.tmosebach.codegenerator.parser;

import de.tmosebach.codegenerator.metamodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SymbolTable {

    private enum EntryType { DataType, Entity }

    abstract class Entry<T extends Type> {
        private EntryType type;
        protected T item;

        public Entry(EntryType type) {
            this.type = type;
        }

        public EntryType getType() {
            return type;
        }

        public T getItem() {
            return item;
        }

        public String getName() {
            return item.getName();
        }
    }

    class DataTypeEntry extends Entry<DataType> {
        public DataTypeEntry(String typeName) {
            super(EntryType.DataType);
            item = new DataType(typeName);
        }
    }

    class EntityEntry extends Entry<Entity> {
        public EntityEntry(String typeName) {
            super(EntryType.Entity);
            item = new Entity(typeName);
        }
    }

    enum OpenType {OpenSuper, OpenAttribut, OpenFeature}

    class OpenEntry {
        private OpenType openType;
        private Entity entity;
        private String name;
        private String type;

        public OpenEntry(OpenType openType, Entity entity, String name, String type) {
            this.openType = openType;
            this.entity = entity;
            this.name = name;
            this.type = type;
        }

        public OpenEntry(Entity entity, String type) {
            this.openType = OpenType.OpenSuper;
            this.entity = entity;
            this.type = type;
        }

        public OpenType getOpenType() {
            return openType;
        }

        public Entity getEntity() {
            return entity;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }

    private List<Entry> entries = new ArrayList<>();
    private List<OpenEntry> openEntries = new ArrayList<>();

    public boolean hasOpen() {
        return !openEntries.isEmpty();
    }

    public void createDataType(String dataTypeName) {
        if (contains(dataTypeName)) {
            throw new RuntimeException("Doppelte DataType-Definition : "+dataTypeName);
        }
        entries.add(new DataTypeEntry(dataTypeName));
        resolvOpen(dataTypeName);
    }

    private boolean contains(String typeName) {
        return findType(typeName).isPresent();
    }

    public Entity createEntity(String entiyName) {
        if (contains(entiyName)) {
            throw new RuntimeException("Doppelte Entity-Definition : " + entiyName);
        }
        EntityEntry entityEntry = new EntityEntry(entiyName);
        entries.add(entityEntry);
        resolvOpen(entiyName);
        return entityEntry.getItem();
    }

    private Optional<Entry> findType(String typeName) {
        return entries.stream()
                .filter(e -> e.getName().equals(typeName))
                .findFirst();
    }

    public void addSuper(Entity actualEntity, String superTypeName) {
        Optional<Entry> entry = findType(superTypeName);
        if(entry.isPresent()) {
            Entry<?> e = entry.get();
            if (e.getType() == EntryType.Entity) {
                actualEntity.setSuperType((Entity) e.getItem());
            } else {
                throw new RuntimeException("Datentyp kann nicht vererben : "+superTypeName);
            }
        } else {
            openEntries.add(new OpenEntry(actualEntity, superTypeName));
        }
    }

    public void createAttribute(Entity actualEntity, String name, String typeName) {
        if(contains(typeName)) {
            actualEntity.addAttribut(new Attribut(name, findType(typeName).get().getItem()));
        } else {
            openEntries.add(new OpenEntry(OpenType.OpenAttribut, actualEntity, name, typeName));
        }
    }

    public void createFeature(Entity actualEntity, String name, String typeName) {
        if(contains(typeName)) {
            actualEntity.addFeature(new Feature(name, findType(typeName).get().getItem()));
        } else {
            openEntries.add(new OpenEntry(OpenType.OpenFeature, actualEntity, name, typeName));
        }
    }

    private void resolvOpen(String typeName) {
        List<OpenEntry> openList = openEntries.stream()
                .filter(o -> o.getType().equals(typeName))
                .collect(Collectors.toList());
        resolvOpen(openList);
    }

    private void resolvOpen(List<OpenEntry> openList) {
        for(OpenEntry entry : openList) {
            switch (entry.getOpenType()) {
                case OpenSuper:
                    addSuper(entry.getEntity(), entry.getType());
                    break;
                case OpenAttribut:
                    createAttribute(entry.getEntity(), entry.getName(), entry.getType());
                    break;
                case OpenFeature:
                    createFeature(entry.getEntity(), entry.getName(), entry.getType());
                    break;
            }
        }
    }

    public List<DataType> getDataTypes() {
        return entries
            .stream()
            .filter(e -> e.getType() == EntryType.DataType)
            .map(e -> ((DataTypeEntry)e).getItem())
            .collect(Collectors.toList());
    }

    public List<Entity> getEntities() {
        return entries
                .stream()
                .filter(e -> e.getType() == EntryType.Entity)
                .map(e -> ((EntityEntry)e).getItem())
                .collect(Collectors.toList());
    }
}
