package ray1024.testtasks.companyservermanipulator.model.response

import ray1024.testtasks.companyservermanipulator.model.dto.EmployeeDto

data class EmployeeListResponse(
    val employees: List<EmployeeDto>
)