package org.example

import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat
import java.io.File

fun main() {
    /*
    Init+config tomcat server, mapping urls, config connector, starting and serving tomcat server
     */
    val tomcat = Tomcat()
    tomcat.setBaseDir(File("target/tomcat").apply { mkdirs() }.absolutePath)

    val ctx = tomcat.addContext("", null)

    mapOf(
        "add"       to AddServlet(),
        "subtract"  to SubtractServlet(),
        "multiply"  to MultiplyServlet(),
        "divide"    to DivideServlet()
    ).forEach { (path, servlet) ->
        Tomcat.addServlet(ctx, path, servlet)
        ctx.addServletMapping("/$path", path)
    }

    val connector = Connector("HTTP/1.1")
    connector.port = 8080
    tomcat.service.addConnector(connector)

    tomcat.start()
    println("Server started: http://localhost:8080")
    println("POST /add  /subtract  /multiply  /divide")
    tomcat.server.await()
}
