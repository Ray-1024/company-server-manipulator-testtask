package ray1024.testtasks.companyservermanipulator.exception

data class ResourceNotFoundException(
    override var message: String
) : RuntimeException()