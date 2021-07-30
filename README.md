****Running the System****
Run ```mvn clean compile package``` on all the services...




database_redis:  
image: redis:latest
container_name: redis_container
ports:
- 6379:6379
 