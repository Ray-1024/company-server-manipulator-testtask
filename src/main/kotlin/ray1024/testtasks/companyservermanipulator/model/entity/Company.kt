package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    @Size(min = 1, max = 256)
    @NotBlank
    var name: String,

    @Column(nullable = false)
    @Size(min = 1, max = 2048)
    @NotBlank
    var description: String,

    @Column(nullable = false)
    var foundedDate: LocalDate,

    @OneToMany(
        mappedBy = "company",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        targetEntity = Department::class
    )
    var departments: List<Department> = mutableListOf()
)