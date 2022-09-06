package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetListUsers extends Simulation {

  val feeder = csv("data/data.csv").random

  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")

  val scn = scenario("Get List Users")
    .exec(http("Get List Users")
      .get("?page=2")
      .check(status.is(200))
    )
    .pause(1)

  //Closed model : Inject so that number of concurrent users in the system ramps linearly from a number to another
  setUp(scn.inject(rampConcurrentUsers(50).to(100).during(20)).protocols(httpProtocol))

}
