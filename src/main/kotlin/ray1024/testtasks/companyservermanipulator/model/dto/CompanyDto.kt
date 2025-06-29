package ray1024.testtasks.companyservermanipulator.model.dto

import java.time.LocalDate

data class CompanyDto(
    val id: Long?,
    val name: String?,
    val description: String?,
    val foundedDate: LocalDate?,
    val departments: List<DepartmentDto>?
)