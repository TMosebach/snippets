mvn archetype:generate -DarchetypeGroupId=com.zenjava -DarchetypeArtifactId=javafx-basic-archetype -DarchetypeVersion=8.1.5

Running Application:
mvn jfx:run

Build executable Jar
mvn clean jfx:jar

Build nativ Installer
mvn clean jfx:native