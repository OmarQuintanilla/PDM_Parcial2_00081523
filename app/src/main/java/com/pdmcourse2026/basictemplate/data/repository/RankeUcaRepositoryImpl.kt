package com.pdmcourse2026.basictemplate.data.repository

// Archivo: data/repository/RankeUcaRepositoryImpl.kt

import com.pdmcourse2026.basictemplate.data.api.KtorClient
import com.pdmcourse2026.basictemplate.data.api.dto.PlaceDto
import com.pdmcourse2026.basictemplate.data.mappers.toModel
import com.pdmcourse2026.basictemplate.model.Place
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class RankeUcaRepositoryImpl : RankeUcaRepository {

    override suspend fun getOptions(): Result<List<Place>> {
        return try {
            val response = KtorClient.client.get("${KtorClient.BASE_URL}/options")

            if (response.status.isSuccess()) {
                val dtos = response.body<List<PlaceDto>>()
                Result.success(dtos.map { it.toModel() })
            } else {
                // Manejo de error 401 u otros
                Result.failure(Exception("Error en la API: ${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun vote(placeId: Int): Result<Unit> {
        return try {
            val response = KtorClient.client.post("${KtorClient.BASE_URL}/vote") {
                contentType(ContentType.Application.Json)
                // OJO: Verifica en Swagger si la API espera {"id": id} o {"optionId": id}
                setBody(mapOf("id" to placeId))
            }

            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error al votar: ${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}