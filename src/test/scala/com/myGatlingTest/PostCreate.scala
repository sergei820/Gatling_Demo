package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostCreate extends Simulation {

  val feeder = csv("data/data.csv").random

  val httpProtocol = http
    .baseUrl("https://reqres.in/api")

  val scn = scenario("Create User")
    .feed(feeder)
    .exec(http("Create User")
    .post("/users")
    .header("content-type", "application/json")
    .asJson
    .body(StringBody(
"""|
 |{
 |    "name": "${name}",
 |    "job": "${job}"
 |}
 |"""

 .stripMargin))
    .check(
      status.is(201),
      jsonPath("$.name") is "Sergei",
      jsonPath("$.job") is "Software Test Automation Engineer"
    ))
  .pause(1)

  setUp(scn.inject(rampUsers(5).during(5)).protocols(httpProtocol))
}
