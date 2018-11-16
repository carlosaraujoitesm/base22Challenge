# Challenge base22
Backend challenge

## Dependencies 
* JSoup   <!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->  (for web scraping)
* commons-lang     <!-- https://mvnrepository.com/artifact/commons-lang/commons-lang --> (for string escaping utils)
* commons-cli <!-- https://mvnrepository.com/artifact/commons-cli/commons-cli --> (for arguments parsing)

## Compile jar with dependencies (into /base22-challenge)
mvn clean compile assembly:single

## Package project with (into /base22-challenge)
mvn package 

## Usage, cd into /base22-challenge/backend-base22-challenge/target/  this is where you'll find the executable
java -jar backend-base22-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar --inputFileName "value" -outputFormat "value" --outputFileName "value" --outputFormat "csv"

## Output, reading the CSV
Open any editor for csv and select delimiter ","

