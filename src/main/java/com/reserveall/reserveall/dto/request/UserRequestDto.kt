package com.reserveall.reserveall.dto.request

import com.reserveall.reserveall.model.Role

data class UserRequestDto(
    val username: String?,
    val email: String?,
    val password: String?,
    val role: Role?
)
