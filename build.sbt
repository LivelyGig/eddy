lazy val sharedScalacOptions =
  Seq("-Xfatal-warnings",
      "-Xfuture",
      "-Xlint",
      "-Yno-adapted-args",
//      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ypartial-unification",
      "-Ywarn-value-discard",
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked")

lazy val wartremoverOptions =
  List("Any",
       "AsInstanceOf",
       "DefaultArguments",
       "EitherProjectionPartial",
       "Enumeration",
       "Equals",
       "ExplicitImplicitTypes",
       "FinalCaseClass",
       "FinalVal",
       "ImplicitConversion",
       "IsInstanceOf",
       "JavaConversions",
       "LeakingSealed",
       "MutableDataStructures",
       "NoNeedForMonad",
       "NonUnitStatements",
       "Nothing",
       "Null",
       "Option2Iterable",
       "Overloading",
       "Product",
       "Return",
       "Serializable",
       "StringPlusAny",
       "Throw",
       "ToString",
       "TraversableOps",
       "TryPartial",
       "Var",
       "While").map((s: String) => s"-P:wartremover:traverser:org.wartremover.warts.$s")

lazy val scalatestVersion    = "3.0.1"
lazy val scalaCheckVersion   = "1.13.4"

lazy val jvmDependencySettings = Seq(
  libraryDependencies ++= Seq(compilerPlugin("org.wartremover" %% "wartremover" % "1.2.1")))

lazy val jsDependencySettings = Seq(
  libraryDependencies ++= Seq.empty,
  jsDependencies ++= Seq(
    "org.webjars.bower" % "tweetnacl" % "0.14.5" / "nacl-fast.min.js" commonJSName "nacl"
  ))

lazy val sharedDependencySettings = Seq(
  resolvers ++= Seq(Resolver.sonatypeRepo("snapshots")),
  libraryDependencies ++= Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
                              "org.scalatest"            %%% "scalatest"               % scalatestVersion  % "test",
                              "org.scalacheck"           %%% "scalacheck"              % scalaCheckVersion % "test"))

lazy val eddyJVMSettings = Seq(
  scalacOptions ++= wartremoverOptions,
  name := "eddy-server") ++ jvmDependencySettings

lazy val eddyJSSettings = Seq(
  skip in packageJSDependencies := false,
  crossTarget in fastOptJS := (classDirectory in eddyJVM in Compile).value / "public" / "js",
  crossTarget in packageJSDependencies := (classDirectory in eddyJVM in Compile).value / "public" / "js",
  name := "eddy-client") ++ jsDependencySettings

lazy val sharedSettings = Seq(
  scalaVersion := "2.12.1",
  version := "1.0-SNAPSHOT",
  compileOrder := CompileOrder.JavaThenScala,
  scalacOptions := sharedScalacOptions) ++ sharedDependencySettings

lazy val eddy = crossProject.in(file("."))
  .settings(sharedSettings: _*)

lazy val eddyJVM: Project =
  eddy.jvm.settings(eddyJVMSettings: _*)

lazy val eddyJS: Project  =
  eddy.js.settings(eddyJSSettings: _*)

lazy val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .aggregate(eddyJVM, eddyJS)
  .settings(
    publish := {},
    publishLocal := {})

