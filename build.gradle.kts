plugins {
	java
	application
	id("org.springframework.boot") version "3.2.1-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.4"
	id("checkstyle")
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Checkstyle>().configureEach {
	reports {
		xml.required = false
		html.required = true
		html.stylesheet = resources.text.fromFile("config/checkstyle/checkstyle.xml")
	}
}

application {
	mainClass = "hexlet.code.AppApplication"
}
