export JAVA_HOME=/usr/lib/jvm/java-11-openjdk

build:
	./mvnw clean package -DskipTests
	docker build . -t shanegibbs/miniweb
	docker push shanegibbs/miniweb

run:
	./mvnw spring-boot:run
