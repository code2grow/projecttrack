REM Windows script for adding various jars

call mvn install:install-file -DgeneratePom -DgroupId=informa -DartifactId=informa -Dversion=0.4.5 -Dpackaging=jar -Dfile=lib\informa.jar

call mvn install:install-file -DgeneratePom -DgroupId=selenium -DartifactId=selenium-java-client-driver -Dversion=0.8 -Dpackaging=jar -Dfile=lib\selenium-java-client-driver.jar

call mvn install:install-file -DgeneratePom -DgroupId=selenium -DartifactId=selenium-server -Dversion=0.8 -Dpackaging=jar -Dfile=lib\selenium-server.jar

call mvn install:install-file -DgeneratePom -DgroupId=cargo -DartifactId=cargo  -Dversion=0.8 -Dpackaging=jar -Dfile=lib\cargo-core-uberjar-0.8.jar

call mvn install:install-file -DgeneratePom -DgroupId=postgres -DartifactId=postgres -Dversion=8.1-407.jdbc3 -Dpackaging=jar -Dfile=lib\postgresql-8.1-407.jdbc3.jar




