package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.{Duration, FiniteDuration, SECONDS}

class GetSingleUser extends Simulation {

  //val testDuration = FiniteDuration(Duration(System.getProperty("testDuration")).toSeconds, SECONDS)
  //val userCount = System.getProperty("userCount").toDouble

  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")

  val scn = scenario("Get Single User")
    .exec(http("Get Single User")
      .get("/2")
      .check(status.is(200))
      .check(jsonPath("$.data.first_name").is("Janet"))
    )
    .pause(1)

  // Open model : Injects users at a constant rate, defined in users per second, during a given duration.
  // Users will be injected at randomized intervals
  setUp(scn
    .inject(constantUsersPerSec(System.getProperty("userCount").toDouble)
    .during(5).randomized)
    .protocols(httpProtocol))

}
