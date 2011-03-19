import sbt._

class Build(info: ProjectInfo) extends DefaultWebProject(info) {

  //repositories
  //casbah
  val bumrel = "Bum Network Releases" at "http://repo.bumnetworks.com/releases/"
  val bumsnap = "Bum Network Snapshots" at "http://repo.bumnetworks.com/snapshots/"
  val scalatools = "Scala Tools" at "http://scala-tools.org/repo-releases"

  // scalatra
  val sonatypeNexusSnapshots = "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  val sonatypeNexusReleases = "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases"


  //depenedencies
  //scalatra
  val scalatra = "org.scalatra" %% "scalatra" % "2.0.0-SNAPSHOT"
  
  // jetty
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.22" % "test"
  val servletApi = "org.mortbay.jetty" % "servlet-api" % "2.5-20081211" % "provided"

  //casbah
  val casbah = "com.novus" % "casbah_2.8.0" % "1.0.8.5"

  //json
  val lift_json = "net.liftweb" %% "lift-json" % "2.1"

}
