# Distributed Rate Limiter (Token Bucket Algorithm)

## Overview

This project implements a distributed-ready API rate limiter using the Token Bucket algorithm with Spring Boot.

It is designed to protect backend services from burst traffic, ensure fair API usage, and improve system reliability under high-concurrency environments.

The service enforces per-user rate limits and demonstrates how real-world systems handle high-QPS traffic similar to architectures used in large-scale distributed systems.

---

## Why Rate Limiting?

Rate limiting is a critical component in modern backend systems to:

- Prevent API abuse and brute-force attacks
- Handle burst traffic safely
- Ensure fair resource allocation across users
- Protect downstream microservices
- Improve backend availability and reliability
- Prevent cascading failures during traffic spikes

This pattern is widely used in FAANG-scale systems and API gateway infrastructures.

---

## Tech Stack

- Java
- Spring Boot
- REST APIs
- Token Bucket Algorithm
- ConcurrentHashMap
- Thread-safe request handling
- Embedded Tomcat

---

## High-Level Architecture

```text
Client Request
      ↓
Spring Boot REST Controller
      ↓
Token Bucket Rate Limiter
      ↓
Protected Backend Service
```

Each user is assigned an independent token bucket.

Requests are allowed only if tokens are available.

---

## Rate Limiting Strategy

- Algorithm: Token Bucket
- Burst Capacity: 10 requests
- Refill Rate: 1 token per second
- Scope: Per user (based on `userId`)

This approach allows short bursts of traffic while enforcing long-term request limits efficiently.

---

## How the Token Bucket Algorithm Works

1. Every user receives a bucket containing tokens.
2. Each incoming request consumes one token.
3. Tokens are automatically refilled over time.
4. If no tokens are available, the request is blocked.

This method provides better burst handling compared to fixed-window rate limiting.

---

## Thread Safety & Concurrency

The implementation uses thread-safe data structures (`ConcurrentHashMap`) to safely handle concurrent API requests in multi-threaded environments.

This prevents:

- Race conditions
- Token inconsistencies
- Concurrent modification issues

under high traffic loads.

---

## API Endpoints

### 1. Health Endpoint

```http
GET /api/health
```

#### Example Response

```json
{
  "status": "running",
  "service": "Distributed Rate Limiter"
}
```

---

### 2. Rate Limited Request Endpoint

```http
GET /api/request?userId=test
```

#### Allowed Response

HTTP Status:

```text
200 OK
```

Body:

```json
{
  "status": "allowed",
  "message": "Request allowed"
}
```

#### Rate Limited Response

HTTP Status:

```text
429 TOO MANY REQUESTS
```

Body:

```json
{
  "status": "blocked",
  "message": "Rate limit exceeded"
}
```

---

## How to Run Locally

### Prerequisites

- Java 17 or higher
- IntelliJ IDEA
- Maven

---

### Steps

1. Clone the repository:

```bash
git clone https://github.com/DharunikaHari-98/distributed-rate-limiter.git
```

2. Open the project in IntelliJ IDEA.

3. Run the Spring Boot application.

4. Access health endpoint:

```http
http://localhost:8080/api/health
```

5. Test rate-limited request:

```http
http://localhost:8080/api/request?userId=test
```

---

## Testing Burst Traffic

### PowerShell

```powershell
1..50 | % { curl http://localhost:8080/api/request?userId=test -UseBasicParsing }
```

This simulates rapid traffic to test request throttling behavior.

---

## Scalability Considerations

Currently, the rate limiter uses in-memory storage for token tracking.

For production-scale distributed systems, this can be extended with:

- Redis-backed distributed token storage
- API Gateway integration
- Kubernetes deployment
- Distributed cache synchronization
- Dynamic per-user rate limits
- Monitoring and metrics dashboards

---

## Real-World Use Cases

- API Gateway traffic protection
- Login brute-force prevention
- Payment API throttling
- Public REST API protection
- Microservice traffic shaping
- DDoS mitigation support

---

## System Design Concepts Demonstrated

- Token Bucket rate limiting
- Thread-safe backend programming
- Concurrent request handling
- HTTP 429 status handling
- Traffic throttling
- Backend reliability engineering
- Distributed systems thinking
- REST API design

---

## Future Improvements

- Redis integration for distributed rate limiting
- Sliding Window algorithm support
- Dynamic rate limits based on user tiers
- Prometheus + Grafana monitoring
- Docker and Kubernetes deployment
- Distributed microservice support

---

## Learning Outcome

This project demonstrates backend engineering concepts used in scalable distributed systems and modern API infrastructures for handling high-concurrency traffic safely and efficiently.
