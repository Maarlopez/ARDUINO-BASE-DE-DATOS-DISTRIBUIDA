docker network create --gateway 172.25.1.1 --subnet 172.25.1.0/24 app_subnet
docker-compose up -d