This is a maven 2 project (maven.apache.org)

To build it type the following commands:

addj2eejars.bat

mvn install

(You might get a few warnings. Also maven might have problems downloading jars from the repository. running the command again usually works)

cd webapp

mvn cargo:start

(This will startup tomcat with the ptrack application http://localhost:8080/ptrack/ 
- edit the pom.xml to point to where you have Tomcat installed)

NOTE:

To run functionalTests you need to make sure that %JAVA_HOME% points to a JDK (not JRE) and that %JAVA_HOME%/bin is in %PATH%
