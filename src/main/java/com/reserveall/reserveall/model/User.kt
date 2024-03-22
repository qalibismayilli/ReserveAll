package com.reserveall.reserveall.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.userdetails.User
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,

    @Column(name = "created_date")
    @CreationTimestamp
    val createdDate: LocalDateTime?,

    @Column(name = "updated_date")
    @UpdateTimestamp
    val updatedDate: LocalDateTime?,

    @Column(name = "username", unique = true)
    val username: String?,

    @Column(name = "email")
    val email: String?,

    @Column(name = "password")
    val password: String?,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: Role?
){
    data class Builder(
        var id: String? = null,
        var createdDate: LocalDateTime? = null,
        var updatedDate: LocalDateTime? = null,
        var username: String? = null,
        var email: String? = null,
        var password: String? = null,
        var role: Role? = null
    ){
        fun username(username: String) = apply { this.username = username }
        fun email(email: String) = apply { this.email = email }
        fun password(password: String) = apply { this.password = password }
        fun role(role: Role) = apply { this.role = role }

        fun build() = User(null, null, null, username, email, password, role);
    }
}
