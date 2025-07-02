package ray1024.testtasks.companyservermanipulator.controller

import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.*
import ray1024.testtasks.companyservermanipulator.model.dto.ServerDto
import ray1024.testtasks.companyservermanipulator.model.mapper.ServerMapper
import ray1024.testtasks.companyservermanipulator.model.response.ServerListResponse
import ray1024.testtasks.companyservermanipulator.model.response.ServerResponse
import ray1024.testtasks.companyservermanipulator.service.ServerService

@RestController
@RequestMapping("/api/servers")
class ServerController(
    val serverService: ServerService
) {
    private val mapper: ServerMapper = Mappers.getMapper(ServerMapper::class.java)

    @GetMapping
    fun getAll(
        @RequestParam(name = "page", defaultValue = "1") pageNumber: Int = 1,
        @RequestParam(name = "size", defaultValue = "10") pageSize: Int = 10,
    ): ServerListResponse {
        return ServerListResponse(serverService.getAll(pageNumber, pageSize).map { mapper.toDto(it) }.toList())
    }

    @PostMapping
    fun create(@RequestBody dto: ServerDto): ServerResponse {
        return ServerResponse(mapper.toDto(serverService.create(dto)))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServerResponse {
        return ServerResponse(mapper.toDto(serverService.get(id)))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: ServerDto): ServerResponse {
        return ServerResponse(mapper.toDto(serverService.update(id, dto)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        serverService.delete(id)
    }
}