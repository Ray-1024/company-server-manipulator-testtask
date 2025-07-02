package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    @Size(min = 1, max = 64)
    @NotBlank
    var firstName: String,

    @Column(nullable = false)
    @Size(min = 1, max = 64)
    @NotBlank
    var lastName: String,

    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 128)
    @NotBlank
    var email: String,

    @Column(nullable = false)
    @Size(min = 1, max = 32)
    @NotBlank
    var position: String,

    @Column(nullable = false)
    var hireDate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department,

    @OneToMany(
        mappedBy = "responsibleEmployee",
        targetEntity = Server::class
    )
    var responsibleForServers: List<Server> = mutableListOf()
)