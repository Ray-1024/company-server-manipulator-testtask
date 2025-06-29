package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var foundedDate: LocalDate,

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
    var departments: List<Department> = mutableListOf()
)