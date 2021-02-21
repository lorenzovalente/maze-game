import CommandLineParser.Options.INITIAL_ROOM
import CommandLineParser.Options.ITEMS
import CommandLineParser.Options.MAZE_FILE
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Option.UNLIMITED_VALUES
import org.apache.commons.cli.Options
import java.io.File


class CommandLineParser private constructor(private val cli: CommandLine) {

    object Options {

        val MAZE_FILE: Option = Option.builder("m").longOpt("maze")
            .argName("")
            .desc("JSON file representing a maze")
            .hasArg()
            .type(File::class.java)
            .required()
            .build()

        val INITIAL_ROOM: Option = Option.builder("r").longOpt("room")
            .argName("")
            .desc("Room ID from which traversal starts")
            .hasArg()
            .type(Number::class.java)
            .required()
            .build()

        val ITEMS: Option = Option.builder("i").longOpt("items")
            .argName("")
            .numberOfArgs(UNLIMITED_VALUES)
            .desc("Collection of items to be sought inside the maze")
            .required()
            .build()
    }

    fun getMazeFile(): File {
        return cli.getParsedOptionValue(MAZE_FILE.opt) as File
    }

    fun getInitialRoom(): Long {
        return (cli.getParsedOptionValue(INITIAL_ROOM.opt) as Number).toLong()
    }

    fun getItems(): Collection<String> {
        return cli.getOptionValues(ITEMS.opt).toSet()
    }

    companion object {

        fun from(args: Array<String>): CommandLineParser {

            val options = Options()
            setOf(MAZE_FILE, INITIAL_ROOM, ITEMS).forEach(options::addOption)
            return CommandLineParser(DefaultParser().parse(options, args))
        }
    }
}

