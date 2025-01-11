# Ecommerce Platform

A minimal ecommerce platform designed to support both sellers and customers. This project was built solely for learning and exploring modern development practices.

---

## Tech Stack

### Backend
- **Spring Boot**: A robust Java framework for quickly and securely developing web applications.
- **Spring Cloud**: Used for building the API Gateway, managing configurations, and enabling communication between microservices.
- **Spring Data JPA**: An ORM library to interact with SQL databases, minimizing manual coding.
- **Payment Integration**: Supports VNPay for seamless payment transactions.

### Database
- **MySQL/PostgreSQL**: For storing structured data such as orders, products, and user information.
- **MongoDB**: Used for unstructured data like product reviews or related metadata.

### Frontend
- **React**: A popular JavaScript library for building user interfaces.
- **TypeScript**: Provides type safety and stability to the codebase.
- **ShadCN/UI**: Modern prebuilt UI components that are easy to integrate and customize.
- **React Query**: Optimizes server-side data interaction with features like caching and prefetching.

### Docker
- **Docker**: Packages microservices into containers, ensuring consistent deployment across environments.

---

## Requirements

- **Java**: Version 21 or above.
- **Docker**: Installed for containerized deployment.

---

## Quick Start

1. **Clone the repository**
```bash
git clone https://github.com/ducvu0907/ecommerce.git
cd ecommerce
```

2. **Run the application**
```bash
sh build-and-run.sh
```

3. **Access the application**
- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8000`

