package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*

@Entity
data class Manufacturer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String
)