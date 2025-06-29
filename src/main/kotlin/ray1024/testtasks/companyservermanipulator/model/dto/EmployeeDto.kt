package ray1024.testtasks.companyservermanipulator.model.dto

import java.time.LocalDate

data class EmployeeDto(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val position: String?,
    val hireDate: LocalDate?,
    val departmentId: Long?,
    val responsibleForServers: List<ServerDto>?
)