package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.entity.Department
import ray1024.testtasks.companyservermanipulator.repository.DepartmentRepository

@Service
class DepartmentService(
    private val departmentRepository: DepartmentRepository
) {
    fun create(department: Department): Department {
        if (departmentRepository.findById(department.id).isPresent) {
            throw ResourceAlreadyExistsException(department.name)
        }
        return departmentRepository.save(department)
    }

    fun update(department: Department): Department {
        if (departmentRepository.findById(department.id).isEmpty) {
            throw ResourceNotFoundException(department.id.toString())
        }
        return departmentRepository.save(department)
    }

    fun delete(id: Long) {
        if (departmentRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException(id.toString())
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