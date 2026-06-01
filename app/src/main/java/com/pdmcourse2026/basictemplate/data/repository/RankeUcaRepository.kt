package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.model.Place

interface RankeUcaRepository {
    suspend fun getOptions(): Result<List<Place>>
    suspend fun vote(placeId: Int): Result<Unit>
}