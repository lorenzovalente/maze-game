package maze

import json.Gson
import json.JsonFileReader

object MazeLoader {

    @JvmStatic
    fun load(jsonFileName: String) : Maze  {

        return Gson.INSTANCE.fromJson(JsonFileReader.readFromPath(path = jsonFileName), Maze::class.java)
    }

    data class Maze(val rooms: Set<Room>)
}
