package ray1024.testtasks.companyservermanipulator.model.mapper

import org.mapstruct.Mapper
import ray1024.testtasks.companyservermanipulator.model.dto.ManufacturerDto
import ray1024.testtasks.companyservermanipulator.model.entity.Manufacturer

@Mapper(uses = [ManufacturerMapper::class], componentModel = "spring")
interface ManufacturerMapper {
    fun toDto(entity: Manufacturer): ManufacturerDto
}