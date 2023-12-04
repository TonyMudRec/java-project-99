build:
	./gradlew clean build

test:
	./gradlew test
	
report:
	./gradlew jacocoTestReport
	
run:
	./gradlew run

.PHONY: build
