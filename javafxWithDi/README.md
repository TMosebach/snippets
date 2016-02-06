# Howto integrate a DI container into a Java FX project

This is an example of integration a DI container into a Java FX application.

This example uses the Spring (Boot) container and is based on a JavaFX quickstart project with the [Java FX basic archetype](https://github.com/javafx-maven-plugin/javafx-basic-archetype).

For integration:
* Add the DI-Container setup und shutdown in the **init()** and **stop()** 
methods of the main-class.
* In fxml-applications like in this quickstart project, the fxml loaders 
controller factory should be replaced with new DI-based factory. 
See **SpringFXMLLoader** an its use) as an example.
