package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.exception.WrongDtoFieldException
import ray1024.testtasks.companyservermanipulator.model.dto.DepartmentDto
import ray1024.testtasks.companyservermanipulator.model.entity.Department
import ray1024.testtasks.companyservermanipulator.repository.CompanyRepository
import ray1024.testtasks.companyservermanipulator.repository.DepartmentRepository
import ray1024.testtasks.companyservermanipulator.repository.EmployeeRepository
import ray1024.testtasks.companyservermanipulator.repository.ServerRepository

@Service
class DepartmentService(
    private val departmentRepository: DepartmentRepository,
    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository,
    private val serverRepository: ServerRepository,
) {
    fun create(dto: DepartmentDto): Department {
        if (dto.id?.let { departmentRepository.findById(it).isPresent } == true) {
            throw ResourceAlreadyExistsException("Department with id ${dto.id} already exists")
        }
        return departmentRepository.save(
            Department(
                id = 0,
                name = dto.name ?: throw WrongDtoFieldException("Department must have a name"),
                location = dto.location ?: throw WrongDtoFieldException("Department must have a location"),
                company = dto.companyId?.let {
                    companyRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Company with id = $it not found") }
                } ?: throw WrongDtoFieldException("Department must have owner company"),
                employees = dto.employeeIds?.map {
                    employeeRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Employee with id = $it not found") }
                } ?: mutableListOf(),
                servers = dto.serverIds?.map {
                    serverRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Server with id = $it not found") }
                } ?: mutableListOf(),
            )
        )
    }

    fun update(id: Long, dto: DepartmentDto): Department {
        val department = departmentRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Department with id = $id not found") }
        return departmentRepository.save(
            Department(
                id = id,
                name = dto.name ?: department.name,
                location = dto.location ?: department.location,
                company = dto.companyId?.let {
                    companyRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Company with id = $it not found") }
                } ?: department.company,
                employees = dto.employeeIds?.map {
                    employeeRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Employee with id = $it not found") }
                } ?: department.employees,
                servers = dto.serverIds?.map {
                    serverRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Server with id = $it not found") }
                } ?: department.servers
            )
        )
    }

    fun delete(id: Long) {
        if (departmentRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException("Department with id = $id not found")
        }
        departmentRepository.deleteById(id)
    }

    fun get(id: Long): Department {
        return departmentRepository.findById(id).get()
    }

    fun getAll(page: Int, size: Int): Page<Department> {
        return departmentRepository.findAll(PageRequest.of(page, size))
    }
}