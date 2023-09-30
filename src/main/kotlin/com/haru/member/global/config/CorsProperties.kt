package com.haru.member.global.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cors")
data class CorsProperties (
    val allowedOrigins: Array<String>
)