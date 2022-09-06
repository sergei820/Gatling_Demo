# Gatling Studying Project

To run the scenario:

    mvn gatling:test '-DclassName=com.myGatlingTest.GetSingleUser' '-DuserCount=100'
    mvn gatling:test '-DclassName=com.myGatlingTest.GetListUsers'
    mvn gatling:test '-DclassName=com.myGatlingTest.GetSingleResource'
    mvn gatling:test '-DclassName=com.myGatlingTest.GetSingleUserNotFound'
    mvn gatling:test '-DclassName=com.myGatlingTest.PostCreate'

mvn gatling:test '-DuserCount=100' '-DtestDuration=10' '-DclassName=com.myGatlingTest.GetSingleUser' 