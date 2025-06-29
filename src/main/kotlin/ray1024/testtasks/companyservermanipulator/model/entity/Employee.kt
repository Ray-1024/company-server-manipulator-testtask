package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var position: String,

    @Column(nullable = false)
    var hireDate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department,

    @OneToMany(mappedBy = "responsibleEmployee")
    var responsibleForServers: List<Server> = mutableListOf()
)