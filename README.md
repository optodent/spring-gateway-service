mvn package spring-boot:repackage

docker build -t gateway-service .

if network is not created, do docker network create --driver bridge custom-temp-network

docker run -d -p 8050:8050 --network="custom-temp-network" --name gateway-service gateway-service 

