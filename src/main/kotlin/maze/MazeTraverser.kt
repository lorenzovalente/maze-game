package maze

import java.util.*
import kotlin.collections.HashSet

class MazeTraverser private constructor() {

    private lateinit var graph: MazeGraph

    private var startingRoom: Room = Room()

    private var visitedRooms: MutableSet<Room> = mutableSetOf()

    private var soughtItems: MutableCollection<Item> = mutableSetOf()

    private var route: Route = Route()

    companion object {

        fun withMaze(mazeGraph: MazeGraph): MazeTraverser {
            val traverser = MazeTraverser()
            traverser.graph = mazeGraph
            return traverser
        }
    }

    fun startingAtRoom(roomId: Long): MazeTraverser {
        startingRoom = graph.getRoomWithId(roomId)
        return this
    }

    fun seekingItems(items: Collection<String>): MazeTraverser {
        soughtItems = items.map { Item(it) }.toMutableSet()
        return this
    }

    fun traverse() : Route {
        if (!startingRoom.hasNeighbour()) {
            return route
        }
        val rooms = Stack<Room>()
        rooms.push(startingRoom)
        visit(rooms)
        return route
    }

    /* using DFS */
    private fun visit(rooms: Stack<Room>) {

        if (rooms.isEmpty() || soughtItems.isEmpty()) {
            return
        }

        val nextRoom = rooms.peek()

        val collectedItems = HashSet(nextRoom.items)

        collectedItems.retainAll(soughtItems)

        route.moves.add(Move.of(nextRoom, collectedItems))

        soughtItems.removeAll(collectedItems)

        visitedRooms.add(nextRoom)

        val neighbors = graph.neighboursOf(nextRoom)
        neighbors.removeAll(visitedRooms)

        try {
            rooms.push(neighbors.first())
        }
        catch (e: NoSuchElementException) {
            rooms.pop()
        }
        visit(rooms)
    }
}
