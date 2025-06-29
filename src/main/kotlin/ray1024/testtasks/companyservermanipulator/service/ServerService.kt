package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.entity.Server
import ray1024.testtasks.companyservermanipulator.repository.ServerRepository

@Service
class ServerService(
    private val serverRepository: ServerRepository
) {
    fun create(server: Server): Server {
        if (serverRepository.findById(server.id).isPresent) {
            throw ResourceAlreadyExistsException(server.name)
        }
        return serverRepository.save(server)
    }

    fun update(server: Server): Server {
        if (serverRepository.findById(server.id).isEmpty) {
            throw ResourceNotFoundException(server.id.toString())
        }
        return serverRepository.save(server)
    }

    fun delete(id: Long) {
        if (serverRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException(id.toString())
        }
        serverRepository.deleteById(id)
    }

    fun get(id: Long): Server {
        return serverRepository.findById(id).get()
    }

    fun getAll(page: Int, size: Int): Page<Server> {
        return serverRepository.findAll(PageRequest.of(page, size))
    }
}