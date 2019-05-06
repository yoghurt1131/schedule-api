package dev.yoghurt1131.calendarapi

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


// @Configuration
class CalendarConfig(private val properties: CalendarProperties) {

    private val APPLICATION_NAME = "Google Calendar API Java Quickstart"
    private val SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY)
    private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH = "tokens"
    val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()

    @Bean
    fun details(): Details{
        var details = Details()
        details.clientId = properties.clientId
        details.authUri = properties.authUri
        details.tokenUri = properties.tokenUri
        details.clientSecret = properties.clientSecret
        details.redirectUris = properties.redirectUris
        return details
    }

    @Bean
    fun credential(details: Details): Credential {
        var googleClientSecrets: GoogleClientSecrets = GoogleClientSecrets();
        googleClientSecrets.installed = details
        val flow = GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }

    @Bean
    fun calendr(credential: Credential): Calendar {
        // Build a new authorized API client service.
        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        val service = Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build()

        return service
    }

}