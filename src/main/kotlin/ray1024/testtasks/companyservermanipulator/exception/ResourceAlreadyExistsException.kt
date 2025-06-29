package ray1024.testtasks.companyservermanipulator.exception

data class ResourceAlreadyExistsException(
    override var message: String
) : RuntimeException()