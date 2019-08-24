package dev.yoghurt1131.calendarapi.service

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import org.springframework.stereotype.Service

interface GoogleOAuth2Service {
    fun buildRequestUrl(scope : Collection<String>) : String

    fun existAccessToken() : Boolean
}

@Service
class GoogleOAuth2ServiceImpl(
        private val googleClientSecrets: GoogleClientSecrets,
        private val googleCredential: GoogleCredential
) : GoogleOAuth2Service {
    override fun buildRequestUrl(scope : Collection<String>) : String {
        return AuthorizationCodeRequestUrl(googleClientSecrets.details.authUri, googleClientSecrets.details.clientId)
                .setRedirectUri(googleClientSecrets.details.redirectUris.first())
                .setScopes(scope)
                .build()
    }

    override fun existAccessToken(): Boolean {
        return !googleCredential.accessToken.isNullOrBlank()
    }
}