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

### Deploy
Clone the repository (it has yaml files to deploy in Kubernetes)
```shell
git clone https://github.com/luis-cdmx/diplo-cloud-mascota-service 
```
#### Run with Docker
. Executed with the bellow command where network net1 must exit or being replaced for an existing one.
Se cambia la versión de la imagen de latest a 1.0
```shell
docker run --network net1 --name mascota-mongo -d mongo:latest
```
#### Run with Kubernetes
Apply the mongo yaml files to run the mongo image on kubernetes
```shell
kubectl apply -f mascota-mongo.yaml
```

### Add required user
Once the image is up connect to it to execute mongo commnds to add the user to connect to it from mascota app.
#### Adjust with Docker
```shell
docker exec -it mascota-mongo mongosh
```
#### Adjust with Kubernates
Get the specific pod name
```shell
kubectl get pods
```
Adjust the pod name to the below command to acces it.
```shell
kubectl exec -it mascota-mongo-deployment-dc7ddfd45-zm2gl -- mongosh
```
### Set the user
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
#### Warning
Tthe mongo db is only fo a quick test then it does not has a persistant disk. So, any time the pod is restarted, the user will have to be added.

## Buid & push docker image 
If you want to create your own image adjuste the image name and tag an run below commands
```shell
docker build -t luisriveracdmx/mascota-rest-app:1.0 .
docker push luisriveracdmx/mascota-rest-app:1.0
```
### Run with Docker
Adjust accordingly the values of network and 
```shell
docker run -it -p 8084:8084 --network net1 -e MONGO_HOSTNAME=mascota-mongo -e MONGO_PORT=27017 -e MONGO_AUTHDB=admin -e MONGO_DB=mascotadb -e MONGO_USER=mascota_owner -e MONGO_PWD=mascota_password -e TOMCAT_PORT=8084 --name test-mascota-app luisriveracdmx/mascota-rest-app
```
### Run with Kubernetes
Run the secrets to se the variables
```shell
kubectl apply -f mascota-secret.yaml
```
Apply the rest yaml files to run the mongo image on kubernetes
```shell
kubectl apply -f mascota-rest.yaml
```
### Run with Tekton
#### Setting the environment
Apply the configuration of the app
```shell
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/mascota-secret.yaml
```
Set the user and password of github and docker. There are not sample files to use your own users. 
```shell
kubectl apply -f github-user-pass.yaml 
kubectl apply -f docker-user-pass.yaml
```
Link those secrets to the "pipeline" service account
#### Set the tasks
```shell
kubectl apply -f https://raw.githubusercontent.com/tektoncd/catalog/main/task/git-clone/0.9/git-clone.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/task-list-directory.yaml
kubectl apply -f kubectl apply -f https://api.hub.tekton.dev/v1/resource/tekton/task/maven/0.3/raw
kubectl apply -f https://api.hub.tekton.dev/v1/resource/tekton/task/buildah/0.6/raw
kubectl apply -f https://api.hub.tekton.dev/v1/resource/tekton/task/kubernetes-actions/0.2/raw
```
#### Optionally apply the taskruns
```shell
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/taskrun-git-clone.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/taskrun-list-dir.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/taskrun-maven.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/taskrun-buildah.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/taskrun-deployment.yaml
```
#### Set and run the pipeline
Apply the definition in the manifest
```shell
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/mascota-pipeline.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/mascota-pipeline-run.yaml
```
### Trigger it from github
Apply the definition in the manifest
```shell
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/trigger/trigger-rbac.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/trigger/event-listener.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/trigger/trigger-template.yaml
kubectl apply -f https://github.com/luis-cdmx/diplo-cloud-mascota-service/blob/main/resources/manifest/tekton/trigger/trigger-binding.yaml
```

## Test
Execute the next `curl` command to validate the deploy of the service. 
```shell
curl -X 'POST' \
  'http://mascota-rest-service:8084/api/mascotas' \
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
* [https://tekton.dev/docs/pipelines/](https://tekton.dev/docs/pipelines/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
