package com.payclip.examplecleancode.server

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.youtube.YouTube

class YoutubeApi(private val appName: String, private val googleAccount: GoogleAccountCredential) {

    private val transport = NetHttpTransport()
    private val jsonFactory: JsonFactory = GsonFactory()

    fun create(): YouTube {
        return YouTube.Builder(transport, jsonFactory, googleAccount)
            .setApplicationName(appName)
            .build()
    }

}
