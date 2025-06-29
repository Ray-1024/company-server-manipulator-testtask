package ray1024.testtasks.companyservermanipulator.model.response

import java.time.Instant

data class ErrorResponse(
    val message: String? = null,
    val details: List<String?>? = null,
    val timestamp: Instant = Instant.now()
)