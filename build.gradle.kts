import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
}

/** gradle.properties 파일에서 버전 정보를 가져옵니다. */
val projectGroup: String by project
val projectVersion: String by project

group = projectGroup
version = projectVersion
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	/** gradle.properties 파일에서 버전 정보를 가져옵니다. */
	val kotlinVersion: String by project

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	
	/* DB */
	implementation("com.h2database:h2")
	
	/* 테스트 라이브러리 */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	
	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:$kotlinVersion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
