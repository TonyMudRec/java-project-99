build:
	./gradlew clean build

test:
	./gradlew test
	
report:
	./gradlew test jacocoTestReport
	
run:
	./gradlew run

.PHONY: build
