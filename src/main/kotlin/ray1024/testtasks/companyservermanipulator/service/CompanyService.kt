package ray1024.testtasks.companyservermanipulator.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ray1024.testtasks.companyservermanipulator.exception.ResourceAlreadyExistsException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.entity.Company
import ray1024.testtasks.companyservermanipulator.repository.CompanyRepository

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    fun create(company: Company): Company {
        if (companyRepository.findById(company.id).isPresent) {
            throw ResourceAlreadyExistsException(company.name)
        }
        return companyRepository.save(company)
    }

    fun update(company: Company): Company {
        if (companyRepository.findById(company.id).isEmpty) {
            throw ResourceNotFoundException(company.id.toString())
        }
        return companyRepository.save(company)
    }

    fun delete(id: Long) {
        if (companyRepository.findById(id).isEmpty) {
            throw ResourceNotFoundException(id.toString())
        }
        companyRepository.deleteById(id)
    }

    fun get(id: Long): Company {
        return companyRepository.findById(id).get()
    }

    fun getAll(page: Int, size: Int): Page<Company> {
        return companyRepository.findAll(PageRequest.of(page, size))
    }
}