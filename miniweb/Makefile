TAG=shanegibbs/miniweb:3

export JAVA_HOME=/usr/lib/jvm/java-11-openjdk

build:
	./mvnw clean package -DskipTests
	docker build . -t $(TAG)
	docker push $(TAG)

run:
	./mvnw spring-boot:run
