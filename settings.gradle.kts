val projectName: String by settings

rootProject.name = projectName

/**
 * build.gradle.kts 파일의 plugin 버전을
 * gradle.properties에서 별도로 관리하기 위해 작성한 코드입니다.
 *
 * @Doc https://mecd.notion.site/build-gradle-kts-47964687096f45ee9f264d9817f34dd9
 */
// @formatter:off
pluginManagement {
    val springBootVersion                   : String by settings
    val springDependencyManagementVersion   : String by settings
    val kotlinVersion                       : String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot"          -> useVersion(springBootVersion)
                "io.spring.dependency-management"   -> useVersion(springDependencyManagementVersion)
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.spring",
                "org.jetbrains.kotlin.plugin.jpa"   -> useVersion(kotlinVersion)
            }
        }
    }
}
// @formatter:on