package com.haru.member.test

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@TestEnvironment
@DataJpaTest
annotation class RepositoryTest