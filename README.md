*** You need to have Docker installed: https://docs.docker.com/get-docker/
- FIRST OF ALL we have to run this command from the root of the project 
    ``` shell
  docker-compose up -d
  ```
- We can check if the containers run properly and see the ports were the databases are expose:
    ``` shell
    docker ps
    ````
- Now we have running two docker containers that expose SQL instances in:
  - localhost:3310
  - localhost:3311 <br>
We can open this instances in the MySQLWorkbench
<br><br>
- SECOND
    Now we have to run both modules (applications): Consumer and Fraud
Consumer exposes in port: 8080
Fraud exposes in port: 8081
<br><br>
Using Postman we execute the POST action

url: localhost:8080/api/v1/consumers
body (raw, JSON):
````json
{
    "firstName" : "Anna",
    "lastName" : "Polinina",
    "email" : "anna.polinina@gmail.com"
}
````

After that we can check our databases, and see that both are affected.
Microservices talk to the Dockerized Volumes, and talk to each other.
