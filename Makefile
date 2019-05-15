export JAVA_HOME=/usr/lib/jvm/java-11-openjdk

build:
	./mvnw clean package -DskipTests

run:
	./mvnw spring-boot:run

jaeger:
	docker run -it --name jaeger \
		-e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
		-p 5775:5775/udp \
		-p 6831:6831/udp \
		-p 6832:6832/udp \
		-p 5778:5778 \
		-p 16686:16686 \
		-p 14268:14268 \
		-p 9411:9411 \
		jaegertracing/all-in-one:1.6

jaeger-delete:
	docker rm -f jaeger