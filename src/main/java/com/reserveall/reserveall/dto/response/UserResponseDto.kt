package com.reserveall.reserveall.dto.response

import com.reserveall.reserveall.model.Role

data class UserResponseDto(
    val username: String?,
    val email: String?,
    val role: Role?
)
