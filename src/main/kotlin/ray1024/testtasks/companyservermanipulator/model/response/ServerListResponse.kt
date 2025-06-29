package ray1024.testtasks.companyservermanipulator.model.response

import ray1024.testtasks.companyservermanipulator.model.dto.ServerDto

data class ServerListResponse(
    val servers: List<ServerDto>
)