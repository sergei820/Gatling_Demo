package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetSingleResource extends Simulation {

  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")

  val scn = scenario("Get Single User")
    .exec(http("Get Single User")
      .get("/2")
      .check(status.is(200))
      .check(jsonPath("$.data.first_name").is("Janet"))
    )
    .pause(1)

  //users goes during 5 sec
  setUp(scn.inject(rampUsers(100).during(5)).protocols(httpProtocol))

}
