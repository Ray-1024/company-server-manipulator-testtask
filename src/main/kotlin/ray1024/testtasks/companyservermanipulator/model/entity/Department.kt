package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
data class Department(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    @Size(min = 1, max = 256)
    @NotBlank
    var name: String,

    @Column(nullable = false)
    @Size(min = 1, max = 1024)
    @NotBlank
    var location: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    var company: Company,

    @OneToMany(
        mappedBy = "department", cascade = [CascadeType.ALL], orphanRemoval = true,
        targetEntity = Employee::class
    )
    var employees: List<Employee> = mutableListOf(),

    @OneToMany(
        mappedBy = "department", cascade = [CascadeType.ALL], orphanRemoval = true,
        targetEntity = Server::class
    )
    var servers: List<Server> = mutableListOf()
)