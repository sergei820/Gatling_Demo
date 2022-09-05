package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetListUsers extends Simulation {

  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")

  val scn = scenario("Get List Users")
    .exec(http("Get List Users")
      .get("?page=2")
      .check(status.is(200))
    )
    .pause(1)

  //all users goes at once
  setUp(scn.inject(atOnceUsers(50)).protocols(httpProtocol))

}
