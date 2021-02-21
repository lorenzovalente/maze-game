package maze

class Move private constructor(val to: Room, val collectedItems: Set<Item>)  {

    companion object {

        fun of(room: Room, collectedItems: Set<Item>): Move {

            return Move(to = room, collectedItems = collectedItems)
        }
    }

}
