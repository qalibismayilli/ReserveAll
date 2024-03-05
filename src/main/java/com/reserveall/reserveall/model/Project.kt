package com.reserveall.reserveall.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "prejects")
data class Project(
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

    @Column(name = "name")
    val name: String?,

    @Column(name = "description")
    val description: String?,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
    val images: List<Image>? = ArrayList()
) {
    data class Builder(
        var id: String? = null,
        var createdDate: LocalDateTime? = null,
        var updatedDate: LocalDateTime? = null,
        var name: String? = null,
        var description: String? = null,
        var images: List<Image>? = null,
    ) {
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun images(image: List<Image>) = apply { this.images = image }

        fun build() = Project(id, createdDate, updatedDate, name, description, images)
    }

}
