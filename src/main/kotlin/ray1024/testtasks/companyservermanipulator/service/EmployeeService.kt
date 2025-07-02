package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.exception.WrongDtoFieldException
import ray1024.testtasks.companyservermanipulator.model.dto.EmployeeDto
import ray1024.testtasks.companyservermanipulator.model.entity.Employee
import ray1024.testtasks.companyservermanipulator.repository.DepartmentRepository
import ray1024.testtasks.companyservermanipulator.repository.EmployeeRepository
import ray1024.testtasks.companyservermanipulator.repository.ServerRepository

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val departmentRepository: DepartmentRepository,
    private val serverRepository: ServerRepository
) {
    fun create(dto: EmployeeDto): Employee {
        if (dto.id?.let { employeeRepository.findById(it).isPresent } == true) {
            throw ResourceAlreadyExistsException("Employee with id ${dto.id} already exists")
        }
        return employeeRepository.save(
            Employee(
                id = 0,
                firstName = dto.firstName ?: throw WrongDtoFieldException("Employee must have a firstName"),
                lastName = dto.lastName ?: throw WrongDtoFieldException("Employee must have a lastName"),
                email = dto.email ?: throw WrongDtoFieldException("Employee must have an email"),
                position = dto.position ?: throw WrongDtoFieldException("Employee must have a position"),
                hireDate = dto.hireDate ?: throw WrongDtoFieldException("Employee must have a hireDate"),
                department = dto.departmentId?.let {
                    departmentRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Department with id = $it not found") }
                } ?: throw WrongDtoFieldException("Employee must have a department"),
                responsibleForServers = dto.responsibleForServerIds?.map {
                    serverRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Server with id = $it not found") }
                } ?: mutableListOf(),
            )
        )
    }

    fun update(id: Long, dto: EmployeeDto): Employee {
        val employee =
            employeeRepository.findById(id)
                .orElseThrow { ResourceNotFoundException("Employee with id = $id not found") }
        return employeeRepository.save(
            Employee(
                id = id,
                firstName = dto.firstName ?: employee.firstName,
                lastName = dto.lastName ?: employee.lastName,
                email = dto.email ?: employee.email,
                position = dto.position ?: employee.position,
                hireDate = dto.hireDate ?: employee.hireDate,
                department = dto.departmentId?.let {
                    departmentRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Department with id = $it not found") }
                } ?: employee.department,
                responsibleForServers = dto.responsibleForServerIds?.map {
                    serverRepository.findById(it)
                        .orElseThrow { ResourceNotFoundException("Server with id = $it not found") }
                } ?: employee.responsibleForServers
            )
        )
    }

    fun delete(id: Long) {
        if (employeeRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException("Employee with id = $id not found")
        }
        employeeRepository.deleteById(id)
    }

    fun get(id: Long): Employee {
        return employeeRepository.findById(id).get()
    }

    fun getAll(page: Int, size: Int): Page<Employee> {
        return employeeRepository.findAll(PageRequest.of(page, size))
    }
}