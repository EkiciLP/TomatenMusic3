import java.io.ByteArrayOutputStream

allprojects {
    group = "net.tomatentum.tomatenmusic3"
    version = "1.0.0-RC1" + (if (!project.hasProperty("release")) ("-" + getGitHash()) else "")
    description = "A simple Discord Music Bot written in Java with Javacord"
}

fun getGitHash(): String {
    val output = ByteArrayOutputStream()
    project.exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = output
    }
    return output.toString().trim()
}