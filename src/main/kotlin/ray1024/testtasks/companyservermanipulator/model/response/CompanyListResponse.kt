package ray1024.testtasks.companyservermanipulator.model.response

import ray1024.testtasks.companyservermanipulator.model.dto.CompanyDto

data class CompanyListResponse(
    val companies: List<CompanyDto>
)