package com.reserveall.reserveall.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime

@Entity
@Table(name = "clients_messages")
data class ClientMessage(
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String,

    @Column(name = "created_date")
    @CreationTimestamp
    val createdDate: LocalDateTime?,

    @Column(name = "name")
    val name: String?,

    @Column(name = "email")
    val email: String?,

    @Column(name = "subject")
    val subject: String?,

    @Column(name = "message")
    val message: String?
) {
    constructor(
        name: String, email: String?,
        subject: String?, message: String?
    ) : this(
        id = "",
        createdDate = null,
        name = name,
        email = email,
        subject = subject,
        message = message
    )
}
