package maze

import maze.error.RoomNotFoundException
import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class MazeTraverserTest {

    @Test
    fun `should traverse a maze`() {

        val maze = MazeGraph.Builder.withFileLocatedAt("src/test/resources/maze.json")
        val route = MazeTraverser.withMaze(maze).startingAtRoom(2).seekingItems(setOf("Knife", "Potted Plant")).traverse()
        assertTrue { route.moves.size >= 3 }
        assertTrue { route.collectedItems().containsAll(setOf(Item("Knife"), Item("Potted Plant"))) }
    }

    @Test
    fun `should return an empty route when traversing a maze without providing a starting room`() {

        val maze = MazeGraph.Builder.withFileLocatedAt("src/test/resources/maze.json")
        val route = MazeTraverser.withMaze(maze).seekingItems(setOf("Knife", "Potted Plant")).traverse()
        assertTrue { route.moves.isEmpty() }
        assertTrue { route.collectedItems().isEmpty() }
    }

    @Test
    fun `should throw RoomNotFoundException when traversing a maze providing a non existing room`() {

        val maze = MazeGraph.Builder.withFileLocatedAt("src/test/resources/maze.json")
        assertFailsWith<RoomNotFoundException> (
            message = "Room with ID: 10 not found!",
            block = { MazeTraverser.withMaze(maze).startingAtRoom(10).seekingItems(setOf("Knife", "Potted Plant")).traverse() }
        )
    }

    @Test
    fun `should return an empty route when traversing a maze seeking no items`() {

        val maze = MazeGraph.Builder.withFileLocatedAt("src/test/resources/maze.json")
        val route = MazeTraverser.withMaze(maze).startingAtRoom(2).traverse()
        assertTrue { route.moves.isEmpty() }
        assertTrue { route.collectedItems().isEmpty() }
    }

    @Test
    fun `should return an empty route when traversing a maze without providing a starting room and seeking no items`() {

        val maze = MazeGraph.Builder.withFileLocatedAt("src/test/resources/maze.json")
        val route = MazeTraverser.withMaze(maze).traverse()
        assertTrue { route.moves.isEmpty() }
        assertTrue { route.collectedItems().isEmpty() }
    }
}
