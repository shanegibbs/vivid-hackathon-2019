FROM openjdk:11-slim

ADD entry.sh \
	checkout/target/minivivid-checkout-0.0.1-SNAPSHOT.jar \
	./web/target/minivivid-web-0.0.1-SNAPSHOT.jar \
	./payment/target/minivivid-payment-0.0.1-SNAPSHOT.jar \
	./fulfillment/target/minivivid-fulfillment-0.0.1-SNAPSHOT.jar \
	/

CMD /entry.sh
