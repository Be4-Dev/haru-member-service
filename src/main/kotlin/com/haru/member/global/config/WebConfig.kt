package com.haru.member.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val corsProperties: CorsProperties,
) : WebMvcConfigurer {

    /* 구글 크롬 보안 정책으로 Origin이 다른 곳에서 브라우저를 거치는 통신을 주고 받으면 CORS 위반이라는 경고 & 제한을 검 */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
                *corsProperties.allowedOrigins
            )
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name(),
            )
            .maxAge(3600)
            .allowCredentials(true)
    }
}