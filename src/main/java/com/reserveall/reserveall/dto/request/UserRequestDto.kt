package com.reserveall.reserveall.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class UserRequestDto(
    @field:NotBlank
    val username: String?,

    @field:Email
    @field:NotEmpty
    val email: String?,

    @field:NotBlank
    val password: String?
)
