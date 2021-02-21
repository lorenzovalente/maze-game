package maze

import com.google.gson.annotations.SerializedName

data class Room(
    val id: Long = -1,

    val name: String = "",

    val north: Long = -1,

    val south: Long = -1,

    val east: Long = -1,

    val west: Long = -1,

    @SerializedName("objects")
    val items: Set<Item> = emptySet()
) {

    fun hasNeighbour(): Boolean {
        return north >= 0 || south >= 0 || east >= 0 || west >= 0
    }
}
