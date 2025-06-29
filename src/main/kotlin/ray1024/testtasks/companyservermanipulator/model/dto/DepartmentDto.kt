package ray1024.testtasks.companyservermanipulator.model.dto

data class DepartmentDto(
    val id: Long?,
    val name: String?,
    val location: String?,
    val companyId: Long?,
    val employeeIds: List<Long>?,
    val serverIds: List<Long>?
)