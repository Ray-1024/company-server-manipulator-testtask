package ray1024.testtasks.companyservermanipulator.model.mapper

import org.mapstruct.Mapper
import ray1024.testtasks.companyservermanipulator.model.dto.EmployeeDto
import ray1024.testtasks.companyservermanipulator.model.entity.Employee

@Mapper(uses = [EmployeeMapper::class], componentModel = "spring")
interface EmployeeMapper {
    fun toDto(entity: Employee): EmployeeDto
}