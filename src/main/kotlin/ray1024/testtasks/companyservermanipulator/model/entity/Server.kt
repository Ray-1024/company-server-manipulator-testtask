package ray1024.testtasks.companyservermanipulator.model.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
data class Server(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    @Size(min = 1, max = 256)
    @NotBlank
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    var manufacturer: Manufacturer,

    @Column(nullable = false, unique = true)
    @Size(min = 5, max = 45)
    @NotBlank
    var ipAddress: String,

    @Column(nullable = false)
    var ramSizeBytes: Long,

    @Column(nullable = false)
    var storageSizeBytes: Long,

    @Column(nullable = false)
    var purchaseDate: LocalDate,

    @Column(nullable = false)
    var isActive: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_employee_id", nullable = false)
    var responsibleEmployee: Employee
)