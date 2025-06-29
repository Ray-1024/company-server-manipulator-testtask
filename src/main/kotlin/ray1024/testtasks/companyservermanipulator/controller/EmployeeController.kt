package ray1024.testtasks.companyservermanipulator.controller

import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.*
import ray1024.testtasks.companyservermanipulator.model.dto.DepartmentDto
import ray1024.testtasks.companyservermanipulator.model.dto.EmployeeDto
import ray1024.testtasks.companyservermanipulator.model.mapper.EmployeeMapper
import ray1024.testtasks.companyservermanipulator.model.response.EmployeeListResponse
import ray1024.testtasks.companyservermanipulator.model.response.EmployeeResponse
import ray1024.testtasks.companyservermanipulator.service.EmployeeService

@RestController
@RequestMapping("/api/employees")
class EmployeeController(
    val employeeService: EmployeeService,
    private val mapper: EmployeeMapper = Mappers.getMapper(EmployeeMapper::class.java)
) {
    @GetMapping
    fun getAll(
        @RequestParam(name = "page", defaultValue = "1") pageNumber: Int = 1,
        @RequestParam(name = "size", defaultValue = "10") pageSize: Int = 10,
    ): EmployeeListResponse {
        return EmployeeListResponse(employeeService.getAll(pageNumber, pageSize).map { mapper.toDto(it) }.toList())
    }

    @PostMapping
    fun create(@RequestBody dto: EmployeeDto): EmployeeResponse {
        return EmployeeResponse(mapper.toDto(employeeService.create(mapper.toEntity(dto))))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): EmployeeResponse {
        return EmployeeResponse(mapper.toDto(employeeService.get(id)))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: EmployeeDto): EmployeeResponse {
        return EmployeeResponse(mapper.toDto(employeeService.update(id, dto)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        employeeService.delete(id)
    }
}