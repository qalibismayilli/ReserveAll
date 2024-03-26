package com.reserveall.reserveall.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class ClientMessageRequestDto(
    @field:NotBlank
    val name: String?,

    @field:Email
    val email: String?,

    @field:NotBlank
    val subject: String?,

    @field:NotBlank
    val message: String?
)
