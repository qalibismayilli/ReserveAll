package com.reserveall.reserveall.dto


data class ClientMessageResponseDto(
    val id: String,
    val name: String?,
    val email: String?,
    val subject: String?,
    val message: String?
){

}
