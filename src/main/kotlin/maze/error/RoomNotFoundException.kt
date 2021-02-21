package maze.error

data class RoomNotFoundException(val id: Long, override val message: String = "Room with ID: $id not found!") : RuntimeException(message)
