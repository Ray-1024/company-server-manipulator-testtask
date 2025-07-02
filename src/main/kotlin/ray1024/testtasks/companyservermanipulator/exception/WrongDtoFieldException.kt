package ray1024.testtasks.companyservermanipulator.exception

data class WrongDtoFieldException(
    override var message: String
) : RuntimeException()