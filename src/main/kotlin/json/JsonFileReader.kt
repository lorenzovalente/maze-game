package json

import org.apache.commons.io.IOUtils
import java.io.FileInputStream

object JsonFileReader {

    @JvmStatic
    fun readFromPath(path: String): String {
        return IOUtils.toString(FileInputStream(path), "UTF-8")
    }
}

