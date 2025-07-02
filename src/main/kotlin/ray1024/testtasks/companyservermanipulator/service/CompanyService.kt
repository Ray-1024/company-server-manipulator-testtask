package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.exception.WrongDtoFieldException
import ray1024.testtasks.companyservermanipulator.model.dto.CompanyDto
import ray1024.testtasks.companyservermanipulator.model.entity.Company
import ray1024.testtasks.companyservermanipulator.repository.CompanyRepository
import ray1024.testtasks.companyservermanipulator.repository.DepartmentRepository

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val departmentRepository: DepartmentRepository
) {
    fun create(dto: CompanyDto): Company {
        if (dto.id?.let { companyRepository.findById(it).isPresent } == true) {
            throw ResourceAlreadyExistsException("Company with id ${dto.id} already exists")
        }
        return companyRepository.save(
            Company(
                id = 0,
                name = dto.name ?: throw WrongDtoFieldException("Company must have a name"),
                description = dto.description ?: throw WrongDtoFieldException("Company must have a description"),
                foundedDate = dto.foundedDate ?: throw WrongDtoFieldException("Company must have a foundedDate"),
                departments = dto.departmentIds?.map {
                    departmentRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Department with id = $it not found") }
                } ?: mutableListOf()
            )
        )
    }

    fun update(id: Long, dto: CompanyDto): Company {
        val company =
            companyRepository.findById(id).orElseThrow { ResourceNotFoundException("Company with id = $id not found") }
        return companyRepository.save(
            Company(
                id = id,
                name = dto.name ?: company.name,
                description = dto.description ?: company.description,
                foundedDate = dto.foundedDate ?: company.foundedDate,
                departments = dto.departmentIds?.map {
                    departmentRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Department with id = $it not found") }
                } ?: company.departments
            )
        )
    }

    fun delete(id: Long) {
        if (companyRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException("Company with id = $id not found")
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