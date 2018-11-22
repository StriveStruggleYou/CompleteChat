mvn -Dmaven.test.skip=true  assembly:assembly
java -jar target/ssy-chat-1.0-SNAPSHOT.jar
