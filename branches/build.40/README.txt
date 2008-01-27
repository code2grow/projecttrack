This is a maven 2 project (maven.apache.org)

To build it type the following command:

mvn install

(You might get a few warnings. Also maven might have problems downloading jars from the repository. running the command again usually works)

(You might also have to install 3rdparty jars from Sun into your local repository as described here http://maven.apache.org/guides/mini/guide-coping-with-sun-jars.html)

You can then run the web app by executing the following commands:

cd webapp
mvn -Denvironment=development cargo:start

(This will download, install and startup tomcat with the ptrack application http://localhost:8080/ptrack/ )

NOTE:

To run functionalTests you need to make sure that %JAVA_HOME% points to a JDK (not JRE) and that %JAVA_HOME%/bin is in %PATH%
