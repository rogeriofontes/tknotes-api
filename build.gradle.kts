import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
	kotlin("plugin.jpa") version "1.4.31"
}

group = "br.com.psi.tknotes"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_13

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
	implementation("io.jsonwebtoken:jjwt:0.6.0")
	implementation("io.sentry:sentry-log4j2:1.7.22")
	implementation("io.springfox:springfox-swagger2:2.7.0")
	implementation("io.springfox:springfox-swagger-ui:2.7.0")
	implementation("com.github.ben-manes.caffeine:caffeine:2.8.5")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	//runtimeOnly("org.hsqldb:hsqldb")
	runtimeOnly("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "13"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	baseName = "TknotesApiApplication"
	version = "0.0.2"
}

/*bootJar {
	destinationDirectory = file(project.buildDir)
}*/
/*tasks.bootJar {
	manifest {
		attributes 'Start-Class': 'br.com.psi.tknotes.tknotesapi.TknotesApiApplication'
	}
}*/
