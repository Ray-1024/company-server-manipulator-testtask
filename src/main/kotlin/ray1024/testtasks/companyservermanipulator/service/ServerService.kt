package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.dto.ServerDto
import ray1024.testtasks.companyservermanipulator.model.entity.Server
import ray1024.testtasks.companyservermanipulator.repository.DepartmentRepository
import ray1024.testtasks.companyservermanipulator.repository.EmployeeRepository
import ray1024.testtasks.companyservermanipulator.repository.ManufacturerRepository
import ray1024.testtasks.companyservermanipulator.repository.ServerRepository

@Service
class ServerService(
    private val serverRepository: ServerRepository,
    private val manufacturerRepository: ManufacturerRepository,
    private val departmentRepository: DepartmentRepository,
    private val employeeRepository: EmployeeRepository
) {
    fun create(server: Server): Server {
        if (serverRepository.findById(server.id).isPresent) {
            throw ResourceAlreadyExistsException(server.name)
        }
        return serverRepository.save(server)
    }

    fun update(id: Long, dto: ServerDto): Server {
        val server = serverRepository.findById(id).orElseThrow { ResourceNotFoundException(id.toString()) }
        return serverRepository.save(
            Server(
                id = id,
                name = dto.name ?: server.name,
                manufacturer = dto.manufacturerId?.let {
                    manufacturerRepository.findById(it).orElseThrow { ResourceNotFoundException(id.toString()) }
                } ?: server.manufacturer,
                ipAddress = dto.ipAddress ?: server.ipAddress,
                ramSizeBytes = dto.ramSizeBytes ?: server.ramSizeBytes,
                storageSizeBytes = dto.storageSizeBytes ?: server.storageSizeBytes,
                purchaseDate = dto.purchaseDate ?: server.purchaseDate,
                isActive = dto.isActive ?: server.isActive,
                department = dto.departmentId?.let {
                    departmentRepository.findById(it).orElseThrow { ResourceNotFoundException(id.toString()) }
                } ?: server.department,
                responsibleEmployee = dto.responsibleEmployeeId?.let {
                    employeeRepository.findById(it).orElseThrow { ResourceNotFoundException(id.toString()) }
                } ?: server.responsibleEmployee
            )
        )
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