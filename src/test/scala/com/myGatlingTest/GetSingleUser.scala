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

  //Gatling has support for two models (Open & Closed) for user injection.
  // The Open model is mainly focused on controlling arrival rate of the users inside the system.
  // The closed model controls concurrency of the users connected to the system.

  //all users goes at once
  //setUp(scn.inject(atOnceUsers(10)).protocols(httpProtocol))

  //users goes during 5 sec
  setUp(scn.inject(rampUsers(100).during(5)).protocols(httpProtocol))
}
