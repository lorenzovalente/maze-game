import maze.MazeGraph
import maze.MazeTraverser

class Application {

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val parser = CommandLineParser.from(args)
            val maze = MazeGraph.Builder.withFileLocatedAt(filePath = parser.getMazeFile().path)
            val route = MazeTraverser.withMaze(maze).startingAtRoom(parser.getInitialRoom()).seekingItems(parser.getItems()).traverse()
            route.print()
        }
    }
}
