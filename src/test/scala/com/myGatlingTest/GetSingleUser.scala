package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetSingleUser extends Simulation {

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
  setUp(scn.inject(constantUsersPerSec(100).during(10).randomized).protocols(httpProtocol))

}
