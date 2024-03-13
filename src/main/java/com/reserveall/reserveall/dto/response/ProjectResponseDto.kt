package com.reserveall.reserveall.dto.response

import com.fasterxml.jackson.databind.BeanDescription

data class ProjectResponseDto(
    val id: String?,
    val name: String?,
    val description: String?,
    val images : List<String>,
)
