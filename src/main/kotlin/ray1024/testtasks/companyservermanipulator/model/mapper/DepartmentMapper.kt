package ray1024.testtasks.companyservermanipulator.model.mapper

import org.mapstruct.Mapper
import ray1024.testtasks.companyservermanipulator.model.dto.DepartmentDto
import ray1024.testtasks.companyservermanipulator.model.entity.Department

@Mapper(uses = [DepartmentMapper::class], componentModel = "spring")
interface DepartmentMapper {
    fun toDto(entity: Department): DepartmentDto
    fun toEntity(dto: DepartmentDto): Department
}