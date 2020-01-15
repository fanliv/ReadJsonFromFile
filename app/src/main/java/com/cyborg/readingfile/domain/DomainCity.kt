package com.cyborg.readingfile.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DomainCity(val id: Int, val name: String)
