package ray1024.testtasks.companyservermanipulator.service

import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.dto.CompanyDto
import ray1024.testtasks.companyservermanipulator.model.entity.Company
import ray1024.testtasks.companyservermanipulator.model.mapper.DepartmentMapper
import ray1024.testtasks.companyservermanipulator.repository.CompanyRepository
import ray1024.testtasks.companyservermanipulator.repository.DepartmentRepository

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val departmentRepository: DepartmentRepository,
    private val mapper: DepartmentMapper = Mappers.getMapper(DepartmentMapper::class.java)
) {
    fun create(company: Company): Company {
        if (companyRepository.findById(company.id).isPresent) {
            throw ResourceAlreadyExistsException(company.name)
        }
        return companyRepository.save(company)
    }

    fun update(id: Long, dto: CompanyDto): Company {
        val company = companyRepository.findById(id).orElseThrow { ResourceNotFoundException(id.toString()) }
        return companyRepository.save(
            Company(
                id = id,
                name = dto.name ?: company.name,
                description = dto.description ?: company.description,
                foundedDate = dto.foundedDate ?: company.foundedDate,
                departments = dto.departmentIds?.map {
                    departmentRepository.findById(it).orElseThrow { ResourceNotFoundException(id.toString()) }
                } ?: company.departments
            )
        )
    }

    fun delete(id: Long) {
        if (companyRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException(id.toString())
        }
        companyRepository.deleteById(id)
    }

    fun get(id: Long): Company {
        return companyRepository.findById(id).get()
    }

    fun getAll(page: Int, size: Int): Page<Company> {
        return companyRepository.findAll(PageRequest.of(page, size))
    }
}