package ray1024.testtasks.companyservermanipulator.model.mapper

import org.mapstruct.Mapper
import ray1024.testtasks.companyservermanipulator.model.dto.ServerDto
import ray1024.testtasks.companyservermanipulator.model.entity.Server

@Mapper(uses = [ServerMapper::class], componentModel = "spring")
interface ServerMapper {
    fun toDto(entity: Server): ServerDto
    fun toEntity(dto: ServerDto): Server
}