# Getting Started

## Project

Implementation of notification microservice for the Mascota application.
The notification will be created for the following event:
- Mascota Register

* [API specification for the project](https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/src/main/resources/mascota.yaml)
* [Properties required to be set to run the app](https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/src/main/resources/application.properties)


## Dependencies
This application requires to be connected to an MongoDB with a defined user. 
For testing pourposes a MongoDB image was used from
[dockerhub](https://hub.docker.com/_/mongo)
. Executed with the bellow command where network net1 must exit or being replaced for an existing one.
```shell
docker run --network net1 --name mascota-mongo -d mongo:latest
```
Once the image is up connect to it to execute mongo commnds.
```shell
docker exec -it mascota-mongo mongosh
```
Create the user. Here some sampling values.
```shell
use admin;
db.createUser(
{
user: "mascota_owner",
pwd: "mascota_password",
roles: [ { role: "userAdmin", db: "mascotadb"
}]
});
db.getUsers();
```
Type exit to quit.

## Deploy
Clone the repository
```shell
git clone https://github.com/urielhdez/diplo-cloud-notificacion-service
```


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
* [Docker MongoDB image](https://hub.docker.com/_/mongo)
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
