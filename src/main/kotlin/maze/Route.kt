package maze

import java.util.*

class Route {

    val moves: LinkedList<Move> = LinkedList()

    fun collectedItems() = moves.flatMap { it.collectedItems }

    fun print() {
        printDashes()
        printHeaders()
        printDashes()
        moves.forEach { printMove(it) }
    }

    private fun printHeaders() {
        println("${"ID".padEnd(15)} ${"Room".padEnd(15)} ${"Objects collected".padEnd(15)}")
    }

    private fun printDashes() {
        println("".padEnd(50, '-'))
    }

    private fun printMove(it: Move) {
        println(
            "${it.to.id.toString().padEnd(15)} ${it.to.name.padEnd(15)} ${
                if (it.collectedItems.isEmpty()) "None" else it.collectedItems.joinToString(
                    ", "
                ) { item -> item.name }.padEnd(15)
            }"
        )
    }
}
