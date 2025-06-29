package ray1024.testtasks.companyservermanipulator.repository

import org.springframework.data.jpa.repository.JpaRepository
import ray1024.testtasks.companyservermanipulator.model.entity.Server

interface ServerRepository : JpaRepository<Server, Long> {
    fun findByDepartmentId(departmentId: Long): List<Server>
    fun findByResponsibleEmployeeId(employeeId: Long): List<Server>
}