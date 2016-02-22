name := "stupid"

organization := "streets"

version := "1.0.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
   "Scalaz Bintray Repo"    at "http://dl.bintray.com/scalaz/releases",
   "Typesafe Repository"    at "http://repo.typesafe.com/typesafe/releases/",
   "Spring Repository"      at "http://repo.springsource.org/milestone",
   "Spray Repository"       at "http://repo.spray.io"
)

libraryDependencies ++= {
   val akkaVersion               = "2.4.1"
   val akkStreamsVersion         = "2.0.3"
   val scalazVersion             = "7.2.0"
   val camelVersion              = "2.15.3"
   val sprayVersion              = "1.3.3"
   val sprayJsonVersion          = "1.3.2"
   val metricsVersion            = "2.2.0"
   val httpClientVersion         = "4.5.1"
   val reactiveMongoVersion      = "0.11.7"
   val commonsVersion            = "1.4.1"
   val scalaTestVersion          = "2.2.4"
   Seq(
      "org.scalaz"                %% "scalaz-core"                  % scalazVersion,
      "com.typesafe.akka"         %% "akka-remote"                   % akkaVersion,
      "com.typesafe.akka"         %% "akka-contrib"                  % akkaVersion,
      "com.typesafe.akka"         %% "akka-camel"                    % akkaVersion,
      "com.typesafe.akka"         %% "akka-slf4j"                    % akkaVersion,
      "com.typesafe.akka"         %% "akka-persistence"              % akkaVersion,
      "com.typesafe.akka"         %% "akka-cluster"                  % akkaVersion,
      "com.typesafe.akka"         %% "akka-cluster-tools"            % akkaVersion,
      "com.typesafe.akka"         %% "akka-stream-experimental"      % akkStreamsVersion,
      "org.apache.camel"          %  "camel-jetty"                   % camelVersion exclude("org.slf4j", "slf4j-log4j12") exclude("log4j", "log4j"),
      "org.apache.camel"          %  "camel-spring"                  % camelVersion exclude("org.slf4j", "slf4j-log4j12") exclude("log4j", "log4j"),
      "org.apache.camel"          %  "camel-jackson"                 % camelVersion exclude("org.slf4j", "slf4j-log4j12") exclude("log4j", "log4j"),
      "io.spray"                  %% "spray-can"                     % sprayVersion,
      "io.spray"                  %% "spray-routing-shapeless2"      % sprayVersion,
      "io.spray"                  %% "spray-client"                  % sprayVersion,
      "io.spray"                  %% "spray-json"                    % sprayJsonVersion,
      "com.yammer.metrics"        %  "metrics-core"                  % metricsVersion,
      "org.apache.httpcomponents" %  "httpclient"                    % httpClientVersion,
      "org.reactivemongo"         %% "reactivemongo"                 % reactiveMongoVersion,
      "commons-validator"         %  "commons-validator"             % commonsVersion,
      "com.typesafe.akka"         %% "akka-testkit"                  % akkaVersion       % "test",
      "io.spray"                  %% "spray-testkit"                 % sprayVersion      % "test",
      "org.scalatest"             %% "scalatest"                     % scalaTestVersion  % "test"
)}

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.8", "-unchecked")

javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked", "-source", "1.8", "-target", "1.8", "-g:vars")

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Managed
EclipseKeys.withSource := true
