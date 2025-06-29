package ray1024.testtasks.companyservermanipulator.model.response

import ray1024.testtasks.companyservermanipulator.model.dto.ManufacturerDto

data class ManufacturerListResponse(
    val manufacturers: List<ManufacturerDto>
)