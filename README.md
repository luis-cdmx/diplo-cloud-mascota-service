# Getting Started

## Project

Implementation of notification microservice for the Mascota application.
The notification will be created for the following event:
- Mascota Register


## Dependencias
This application requires to be connected to an MongoDB.
For testing pourposes it was used the one from
[dockerhub](https://hub.docker.com/_/mongo)
It was extracted using: 
```shell
docker pull mongo
```
The MongoDB user mus be created before running the app. Below is one sample to set them. The values could be different but they must be set as variables when deploying the app.
```shell
use admin;
db.createUser(
{
user: "mascota_owner",
pwd: "mascota_password",
roles: [ { role: "userAdmin", db: "mascotadb"
}]
});
```

## Deploy

`// TODO `

## Test

Execute the next `curl` command to validate the deploy of the service. 
```shell
curl -X 'POST' \
  'http://localhost:8084/api/mascotas' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    "nombre": "Solovino"
}'
```

The expected result should looks like:

```
{
    "id": "65172566daeae0673186f249",
    "nombre": "Solovino"
}
```
 
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.15/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.15/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.15/reference/htmlsingle/index.html#web)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.7.15/reference/htmlsingle/index.html#data.nosql.mongodb)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
