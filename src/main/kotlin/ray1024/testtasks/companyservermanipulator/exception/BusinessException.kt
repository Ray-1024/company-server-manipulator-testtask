package ray1024.testtasks.companyservermanipulator.exception

data class BusinessException(
    override var message: String
) : RuntimeException()