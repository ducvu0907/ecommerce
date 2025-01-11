#!/bin/bash

# stop if any script fails
set -e

BACKEND_DIR="./backend"
SERVICES=("api-gateway" "cart-service" "discount-service" "order-service" "payment-service" "product-service" "review-service" "user-service")

cleanup() {
  echo "Stopping and cleaning up Docker Compose for local databases..."
  docker compose -f ./backend/docker-compose.local.yml down -v
}

trap cleanup EXIT

# start dbs to mvn package
echo "Starting local databases to build JAR files..."
docker compose -f ./backend/docker-compose.local.yml up -d --build

echo "Building JAR files for all backend services..."

# build jar files
for SERVICE in "${SERVICES[@]}"; do
  SERVICE_PATH="$BACKEND_DIR/$SERVICE"
  echo "Building $SERVICE_PATH..."
  (cd "$SERVICE_PATH" && ./mvnw clean package)
  echo "Built $SERVICE_PATH successfully!"
done

echo "All services built successfully!"

echo "Stopping local databases..."
cleanup

echo "Starting Docker Compose for the application..."

# run everything
docker compose up --build

echo "Docker Compose is running!"

