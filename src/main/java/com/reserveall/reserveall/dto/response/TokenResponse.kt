package com.reserveall.reserveall.dto.response


data class TokenResponse(
    val accessToken: String?,
    val userDto: UserResponseDto?
)