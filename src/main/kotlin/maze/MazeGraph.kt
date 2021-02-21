package maze

import maze.error.RoomNotFoundException

class MazeGraph private constructor() {

    private lateinit var nodes: Map<Long, Room>

    object Builder {

        fun withFileLocatedAt(filePath: String): MazeGraph {
            val graph = MazeGraph()
            graph.nodes = MazeLoader.load(filePath).rooms.map { it.id to it }.toMap()
            return graph
        }
    }

    fun neighboursOf(room: Room) : MutableSet<Room>{
        return setOf(room.north, room.south, room.east, room.west)
            .filter { it > -1 }
            .map { getRoomWithId(it) }.toMutableSet()
    }

     fun getRoomWithId(id: Long): Room {
        return nodes[id] ?: throw RoomNotFoundException(id)
    }
}
