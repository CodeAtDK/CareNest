data class Message(
    val senderId: String? = null,
    val message: String? = null,
    val timestamp: Long = 0,
    var seen: Boolean = false
)

