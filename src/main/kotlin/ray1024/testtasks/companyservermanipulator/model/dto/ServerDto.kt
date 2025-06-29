package ray1024.testtasks.companyservermanipulator.model.dto

import java.time.LocalDate

data class ServerDto(
    val id: Long?,
    val name: String?,
    val manufacturerId: Long?,
    val ipAddress: String?,
    val ramSizeBytes: Long?,
    val storageSizeBytes: Long?,
    val purchaseDate: LocalDate?,
    val isActive: Boolean?,
    val departmentId: Long?,
    val responsibleEmployeeId: Long?
)