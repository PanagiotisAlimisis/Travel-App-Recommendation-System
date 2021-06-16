# Travel App

This is a GUI application using Java Swing which helps travellers to select their dream place to visit. There are two techniques to decide on which city each traveller should go. Content based filtering and collaborative filtering. All cities are saved into an Oracle database, while travellers are kept into a Json file.

APIs used: 
```
OpenWeatherMap
MediaWiki
Teleport
```

Instructions to run this project:

Download project
```
Clone project
```

Compile project
```
mvn clean compile assembly:single
```

Run project
```
java -cp target/org.it21902-0.0.1-SNAPSHOT-jar-with-dependencies.jar window.MainWindow
```
