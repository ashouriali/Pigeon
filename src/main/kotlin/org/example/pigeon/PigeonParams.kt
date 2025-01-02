package org.example.pigeon

import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option

class PigeonParams {

    companion object {
        fun create(args: Array<String>): PigeonParams {
            val params = PigeonParams()
            runCatching {
                CmdLineParser(params).also { it.parseArgument(*args) }
            }.onFailure {
                error("Could not parse commands!")
            }
            return params
        }
    }

    @Option(name = "-p", usage = "define port of app server")
    var port: Int = 7071

    @Option(name = "-m", usage = "define mode of app server")
    var mode: String = "dev"

}