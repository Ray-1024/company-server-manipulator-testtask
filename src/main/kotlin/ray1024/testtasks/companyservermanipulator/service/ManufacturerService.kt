package ray1024.testtasks.companyservermanipulator.service

import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.entity.Manufacturer
import ray1024.testtasks.companyservermanipulator.repository.ManufacturerRepository

@Service
@AllArgsConstructor
class ManufacturerService(
    private val manufacturerRepository: ManufacturerRepository
) {
    fun create(manufacturer: Manufacturer): Manufacturer {
        if (manufacturerRepository.findById(manufacturer.id).isPresent) {
            throw ResourceAlreadyExistsException(manufacturer.name)
        }
        return manufacturerRepository.save(manufacturer)
    }

    fun update(manufacturer: Manufacturer): Manufacturer {
        if (manufacturerRepository.findById(manufacturer.id).isEmpty) {
            throw ResourceNotFoundException(manufacturer.id.toString())
        }
        return manufacturerRepository.save(manufacturer)
    }

    fun delete(id: Long) {
        if (manufacturerRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException(id.toString())
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