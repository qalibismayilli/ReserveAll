package com.reserveall.reserveall.dto

import org.springframework.web.multipart.MultipartFile

data class ImageModel(
    val name: String?,
    val file: MultipartFile?
)
