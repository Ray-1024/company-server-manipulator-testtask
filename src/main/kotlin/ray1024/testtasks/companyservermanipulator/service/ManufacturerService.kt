package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.exception.WrongDtoFieldException
import ray1024.testtasks.companyservermanipulator.model.dto.ManufacturerDto
import ray1024.testtasks.companyservermanipulator.model.entity.Manufacturer
import ray1024.testtasks.companyservermanipulator.repository.ManufacturerRepository

@Service
class ManufacturerService(
    private val manufacturerRepository: ManufacturerRepository
) {
    fun create(dto: ManufacturerDto): Manufacturer {
        if (dto.id?.let { manufacturerRepository.findById(it).isPresent } == true) {
            throw ResourceAlreadyExistsException("Manufacturer with id ${dto.id} already exists")
        }
        return manufacturerRepository.save(
            Manufacturer(
                id = 0,
                name = dto.name ?: throw WrongDtoFieldException("Manufacturer must have a name"),
                description = dto.description ?: throw WrongDtoFieldException("Manufacturer must have a description"),
            )
        )
    }

    fun update(id: Long, dto: ManufacturerDto): Manufacturer {
        val manufacturer = manufacturerRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Manufacturer with id = $id not found") }
        return manufacturerRepository.save(
            Manufacturer(
                id = id,
                name = dto.name ?: manufacturer.name,
                description = dto.description ?: manufacturer.description
            )
        )
    }

    fun delete(id: Long) {
        if (manufacturerRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException("Manufacturer with id = $id not found")
        }
        manufacturerRepository.deleteById(id)
    }

    fun get(id: Long): Manufacturer {
        return manufacturerRepository.findById(id).get()
    }

    fun getAll(page: Int, size: Int): Page<Manufacturer> {
        return manufacturerRepository.findAll(PageRequest.of(page, size))
    }
}