package ray1024.testtasks.companyservermanipulator.model.mapper

import org.mapstruct.Mapper
import ray1024.testtasks.companyservermanipulator.model.dto.CompanyDto
import ray1024.testtasks.companyservermanipulator.model.entity.Company

@Mapper(uses = [CompanyMapper::class], componentModel = "spring")
interface CompanyMapper {
    fun toDto(entity: Company): CompanyDto
}