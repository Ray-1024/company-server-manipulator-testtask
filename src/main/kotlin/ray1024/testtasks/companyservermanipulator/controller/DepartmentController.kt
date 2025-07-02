package ray1024.testtasks.companyservermanipulator.controller

import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.*
import ray1024.testtasks.companyservermanipulator.model.dto.DepartmentDto
import ray1024.testtasks.companyservermanipulator.model.mapper.DepartmentMapper
import ray1024.testtasks.companyservermanipulator.model.response.DepartmentListResponse
import ray1024.testtasks.companyservermanipulator.model.response.DepartmentResponse
import ray1024.testtasks.companyservermanipulator.service.DepartmentService

@RestController
@RequestMapping("/api/divisions")
class DepartmentController(
    val departmentService: DepartmentService
) {
    private val mapper: DepartmentMapper = Mappers.getMapper(DepartmentMapper::class.java)

    @GetMapping
    fun getAll(
        @RequestParam(name = "page", defaultValue = "1") pageNumber: Int = 1,
        @RequestParam(name = "size", defaultValue = "10") pageSize: Int = 10,
    ): DepartmentListResponse {
        return DepartmentListResponse(departmentService.getAll(pageNumber, pageSize).map { mapper.toDto(it) }.toList())
    }

    @PostMapping
    fun create(@RequestBody dto: DepartmentDto): DepartmentResponse {
        return DepartmentResponse(mapper.toDto(departmentService.create(dto)))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): DepartmentResponse {
        return DepartmentResponse(mapper.toDto(departmentService.get(id)))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: DepartmentDto): DepartmentResponse {
        return DepartmentResponse(mapper.toDto(departmentService.update(id, dto)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        departmentService.delete(id)
    }
}