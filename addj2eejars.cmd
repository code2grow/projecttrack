REM Windows script for adding JTA jar

call mvn install:install-file -DgeneratePom -DgroupId=javax.jsf -DartifactId=jsf-api -Dversion=jsf-1_1_01 -Dpackaging=jar -Dfile=lib\jsf-api.jar
call mvn install:install-file -DgeneratePom -DgroupId=javax.jsf -DartifactId=jsf-impl -Dversion=jsf-1_1_01 -Dpackaging=jar -Dfile=lib\jsf-impl.jar
call mvn install:install-file -DgeneratePom -DgroupId=informa -DartifactId=informa -Dversion=0.4.5 -Dpackaging=jar -Dfile=lib\informa.jar

call mvn install:install-file -DgeneratePom -DgroupId=selenium -DartifactId=selenium-java-client-driver -Dversion=0.8 -Dpackaging=jar -Dfile=lib\selenium-java-client-driver.jar

call mvn install:install-file -DgeneratePom -DgroupId=selenium -DartifactId=selenium-server -Dversion=0.8 -Dpackaging=jar -Dfile=lib\selenium-server.jar

call mvn install:install-file -DgeneratePom -DgroupId=cargo -DartifactId=cargo  -Dversion=0.8 -Dpackaging=jar -Dfile=lib\cargo-core-uberjar-0.8.jar

call mvn install:install-file -DgeneratePom -DgroupId=postgres -DartifactId=postgres -Dversion=8.1-407.jdbc3 -Dpackaging=jar -Dfile=lib\postgresql-8.1-407.jdbc3.jar

call mvn install:install-file -DgeneratePom -DgroupId=com.glassbox -DartifactId=glassbox -Dversion=2.0-alpha -Dpackaging=war -Dfile=lib\glassbox-2.0-alpha.war

call mvn install:install-file -DgeneratePom -DgroupId=net.chrisrichardson -DartifactId=ormunit-hibernate -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=lib\ormunit-hibernate-1.0-SNAPSHOT.jar

