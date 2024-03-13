package com.reserveall.reserveall.dto.request

import jakarta.validation.constraints.NotBlank

data class ProjectRequestDto(
    @field:NotBlank
    val name: String?,
    @field:NotBlank
    val description: String?
)
