# Posts API Project

##Description
This project expose endpoints that allow manage post posted by users. The endpoints and how to use detailed bellow.

Project written in java with spring-boot (version: 2.5.3)
and using *H2* (https://www.h2database.com/html/main.html)
as database that can be embedded in Java applications.

On startup, posts.csv file (located in resources directory) with new posts, loaded to db for convenience testing. You may add more entries or alternatively, remove existed ones.

###PreRequisite:
- Java 11
- maven 3.6.3 (might work with lower version)

###Steps to run:
> git clone https://github.com/haimgil/postsapi.git

> mvn package 

> mvn spring-boot run

Optional:

Allows get runtime in millis (default: nanos):
> mvn spring-boot:run -Dspring-boot.run.arguments="--time.unit=millis"

###API endpoints:
####Create post
> curl --location --request POST 'localhost:8080/steps/v0/posts' \
--header 'Content-Type: application/json' \
--data-raw '{
"post": {
"title": "String",
"body": "String"
},
"user":{
"id":Long,
"firstName": "String",
"lastName": "String"
}
}'

NOTE: all body parameters are mandatory!
####Get posts
>curl --location --request GET 'localhost:8080/steps/v0/posts'

Optional query parameters:
* userId: allows get all posts of the specified user id
* offset: allow change default offset (0)
* limit: allow change default limit (10)
####Get total post number
>curl --location --request GET 'localhost:8080/steps/v0/postnumber'
####Get top creators
>curl --location --request GET 'localhost:8080/steps/v0/statistics/topcreators'
####Get average runtime
>curl --location --request GET 'localhost:8080/steps/v0/statistics/runtimes'