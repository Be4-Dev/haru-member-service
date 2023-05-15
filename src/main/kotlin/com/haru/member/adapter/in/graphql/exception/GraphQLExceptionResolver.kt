package com.haru.member.adapter.`in`.graphql.exception

import com.haru.member.application.exception.BusinessException
import graphql.ErrorClassification
import graphql.ErrorType
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component
import org.springframework.validation.BindException

//@formatter:off

@Component
class GraphQLExceptionResolver : DataFetcherExceptionResolverAdapter() {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)

        const val HTTP_STATUS_KEY   = "httpStatus"
        const val ERROR_CODE_KEY    = "errorCode"
    }

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (ex) {
            is BindException        -> toGraphQLError(ex, env)
            is BusinessException    -> toGraphQLError(ex, env)
            is Exception            -> toGraphQLError(ex)
            else                    -> super.resolveToSingleError(ex, env)
        }
    }

    private fun toGraphQLError(ex: BindException, env: DataFetchingEnvironment): GraphQLError? {
        return when (val fieldError = ex.bindingResult.fieldError) {
            null -> null
            else -> when (fieldError.code) {
                "typeMismatch"  -> createGraphQLError(exception = ex, errorType = ErrorType.ValidationError, env = env)
                else            -> null
            }
        }
    }

    /**
     * Application 레이어에서 발생한 비즈니스 로직의 GraphQL 예외 객체를 만듭니다.
     */
    private fun toGraphQLError(ex: BusinessException, env: DataFetchingEnvironment): GraphQLError? {
        return createGraphQLError(
            exception   = ex,
            errorType   = ex.errorType,
            env         = env,
            extensions  = mapOf(
                HTTP_STATUS_KEY to ex.httpStatus,
                ERROR_CODE_KEY  to ex.errorCode,
            )
        )
    }

    private fun toGraphQLError(ex: Throwable): GraphQLError? {
        return createGraphQLError(
            exception   = ex,
            errorType   = ErrorType.DataFetchingException,
        )
    }

    private fun createGraphQLError(
        exception   : Throwable,
        errorType   : ErrorClassification,
        extensions  : Map<String, Any?>? = null,
        env         : DataFetchingEnvironment? = null,
    ): GraphQLError? {
        log.error("Exception while handling request: ${exception.message}", exception)

        val graphQLError = GraphqlErrorBuilder
                        .newError()
                        .message(exception.message)
                        .errorType(errorType)

        extensions?.let {
            graphQLError.extensions(extensions)
        }

        env?.let {
            graphQLError.path(it.executionStepInfo.path)
                        .location(it.field.sourceLocation)
        }

        return graphQLError.build()
    }

}