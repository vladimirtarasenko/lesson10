javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/AddPatientByStAX.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/AddPatientsByDOM.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/AddPatientsLocally.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/DoublePatientException.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/GetPatientsInfo.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/GetPatientsRemotelyByStAX.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/PatientCheck.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/StartMenu.java
javac -J-Dfile.encoding=cp866 -encoding utf8 -sourcepath ./src src/XML_Main.java

java -classpath ./src XML_Main
Pause