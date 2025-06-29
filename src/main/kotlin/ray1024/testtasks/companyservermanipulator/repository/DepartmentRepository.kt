package ray1024.testtasks.companyservermanipulator.repository

import org.springframework.data.jpa.repository.JpaRepository
import ray1024.testtasks.companyservermanipulator.model.entity.Department

interface DepartmentRepository : JpaRepository<Department, Long> {
}