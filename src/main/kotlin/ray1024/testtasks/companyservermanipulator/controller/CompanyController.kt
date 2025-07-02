package ray1024.testtasks.companyservermanipulator.controller

import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.*
import ray1024.testtasks.companyservermanipulator.model.dto.CompanyDto
import ray1024.testtasks.companyservermanipulator.model.mapper.CompanyMapper
import ray1024.testtasks.companyservermanipulator.model.response.CompanyListResponse
import ray1024.testtasks.companyservermanipulator.model.response.CompanyResponse
import ray1024.testtasks.companyservermanipulator.service.CompanyService

@RestController
@RequestMapping("/api/companies")
class CompanyController(
    val companyService: CompanyService
) {
    private val mapper: CompanyMapper = Mappers.getMapper(CompanyMapper::class.java)

    @GetMapping
    fun getAll(
        @RequestParam(name = "page", defaultValue = "1") pageNumber: Int = 1,
        @RequestParam(name = "size", defaultValue = "10") pageSize: Int = 10,
    ): CompanyListResponse {
        return CompanyListResponse(companyService.getAll(pageNumber, pageSize).map { mapper.toDto(it) }.toList())
    }

    @PostMapping
    fun create(@RequestBody dto: CompanyDto): CompanyResponse {
        return CompanyResponse(mapper.toDto(companyService.create(dto)))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CompanyResponse {
        return CompanyResponse(mapper.toDto(companyService.get(id)))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: CompanyDto): CompanyResponse {
        return CompanyResponse(mapper.toDto(companyService.update(id, dto)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        companyService.delete(id)
    }
}