package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
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
    fun create(employee: Employee): Employee {
        if (employeeRepository.findById(employee.id).isPresent) {
            throw ResourceAlreadyExistsException(employee.id.toString())
        }
        return employeeRepository.save(employee)
    }

    fun update(id: Long, dto: EmployeeDto): Employee {
        val employee = employeeRepository.findById(id).orElseThrow { ResourceNotFoundException(id.toString()) }
        return employeeRepository.save(
            Employee(
                id = id,
                firstName = dto.firstName ?: employee.firstName,
                lastName = dto.lastName ?: employee.lastName,
                email = dto.email ?: employee.email,
                position = dto.position ?: employee.position,
                hireDate = dto.hireDate ?: employee.hireDate,
                department = dto.departmentId?.let {
                    departmentRepository.findById(it).orElseThrow { ResourceNotFoundException(id.toString()) }
                } ?: employee.department,
                responsibleForServers = dto.responsibleForServerIds?.map {
                    serverRepository.findById(it).orElseThrow { ResourceNotFoundException(id.toString()) }
                } ?: employee.responsibleForServers
            )
        )
    }

    fun delete(id: Long) {
        if (employeeRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException(id.toString())
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