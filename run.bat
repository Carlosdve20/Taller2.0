javac -d bin -cp .:lib/mysql-connector-j-9.2.0.jar ./src/dao/*.java ./src/model/*.java ./src/*.javagit 
java -cp "./bin;.:lib/mysql-connector-j-9.2.0.jar" Main