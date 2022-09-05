package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetSingleUserNotFound extends Simulation {

  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")

  val scn = scenario("Get Single User")
    .exec(http("Get Single User")
      .get("/23")
      .check(status.is(404))
    )
    .pause(1)

  //Open model : Injects a given number of users at once
  setUp(scn.inject(atOnceUsers(500)).protocols(httpProtocol))

}
