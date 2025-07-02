package ray1024.testtasks.companyservermanipulator.controller

import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.*
import ray1024.testtasks.companyservermanipulator.model.dto.ManufacturerDto
import ray1024.testtasks.companyservermanipulator.model.mapper.ManufacturerMapper
import ray1024.testtasks.companyservermanipulator.model.response.ManufacturerListResponse
import ray1024.testtasks.companyservermanipulator.model.response.ManufacturerResponse
import ray1024.testtasks.companyservermanipulator.service.ManufacturerService

@RestController
@RequestMapping("/api/manufacturers")
class ManufacturerController(
    val manufacturerService: ManufacturerService
) {
    private val mapper: ManufacturerMapper = Mappers.getMapper(ManufacturerMapper::class.java)

    @GetMapping
    fun getAll(
        @RequestParam(name = "page", defaultValue = "1") pageNumber: Int = 1,
        @RequestParam(name = "size", defaultValue = "10") pageSize: Int = 10,
    ): ManufacturerListResponse {
        return ManufacturerListResponse(manufacturerService.getAll(pageNumber, pageSize).map { mapper.toDto(it) }
            .toList())
    }

    @PostMapping
    fun create(@RequestBody dto: ManufacturerDto): ManufacturerResponse {
        return ManufacturerResponse(mapper.toDto(manufacturerService.create(dto)))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ManufacturerResponse {
        return ManufacturerResponse(mapper.toDto(manufacturerService.get(id)))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: ManufacturerDto): ManufacturerResponse {
        return ManufacturerResponse(mapper.toDto(manufacturerService.update(id, dto)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        manufacturerService.delete(id)
    }
}