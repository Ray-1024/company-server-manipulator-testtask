package ray1024.testtasks.companyservermanipulator.repository

import org.springframework.data.jpa.repository.JpaRepository
import ray1024.testtasks.companyservermanipulator.model.entity.Employee

interface EmployeeRepository : JpaRepository<Employee, Long> {
}