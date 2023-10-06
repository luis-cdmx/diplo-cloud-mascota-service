Implementación del microservice de la aplicación Mascota. La notificación será creada para el siguiente evento:

Registro Mascota 


Deploy
// TODO 


Test
Execute the next curl command to validate the deploy of the service.

curl -X 'POST' \
  'http://localhost:8084/api/mascotas' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    "nombre": "Solovino"
}' 
The expected result should looks like:

{
    "id": "65172566daeae0673186f249",
    "nombre": "Solovino"
}
Reference Documentation
For further reference, please consider the following sections:
