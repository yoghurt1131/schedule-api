package dev.yoghurt1131.calendarapi

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.calendar.CalendarScopes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class GoogleOAuth2Interceptor(
        private val googleClientSecrets: GoogleClientSecrets,
        private val googleCredential: GoogleCredential
) : HandlerInterceptor {

    private val SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY)
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info("Start - GoogleOAuth2Interceptor $request, $response, $handler")
        val handerMethod = handler as HandlerMethod
        val annotation = handerMethod.getMethodAnnotation(GoogleOAuth2::class.java)
        annotation?.let {
            logger.info("Check Google OAuth2....")
            if(googleCredential.accessToken.isNullOrBlank()) {
                logger.info("No accessToken. Redirect Google Authentication Server")
                val requestUrl = AuthorizationCodeRequestUrl(googleClientSecrets.details.authUri, googleClientSecrets.details.clientId)
                        .setRedirectUri(googleClientSecrets.details.redirectUris.first())
                        .setScopes(SCOPES)
                        .build()
                 response.sendRedirect(requestUrl)
                return false
            }
            logger.info("AccessToken has found. Continue.")
        }
        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        logger.info("End - GoogleOAuth2Interceptor")
        super.postHandle(request, response, handler, modelAndView)
    }

}

