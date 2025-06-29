package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.entity.Employee
import ray1024.testtasks.companyservermanipulator.repository.EmployeeRepository

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
    fun create(employee: Employee): Employee {
        if (employeeRepository.findById(employee.id).isPresent) {
            throw ResourceAlreadyExistsException(employee.id.toString())
        }
        return employeeRepository.save(employee)
    }

    fun update(employee: Employee): Employee {
        if (employeeRepository.findById(employee.id).isEmpty) {
            throw ResourceNotFoundException(employee.id.toString())
        }
        return employeeRepository.save(employee)
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