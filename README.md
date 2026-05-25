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
- Multi-threaded request handling

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

Requests are allowed only if tokens are available in the bucket.

---

## Rate Limiting Strategy

- Algorithm: Token Bucket
- Burst Capacity: 10 requests
- Refill Rate: 1 token per second
- Scope: Per user (based on `userId`)

This approach allows short bursts of traffic while enforcing long-term rate limits efficiently.

---

## How the Token Bucket Algorithm Works

1. Every user receives a bucket containing tokens.
2. Each incoming API request consumes one token.
3. Tokens are automatically refilled over time.
4. If tokens are unavailable, the request is rejected.

This method provides better burst handling compared to fixed-window rate limiting.

---

## Thread Safety & Concurrency

The implementation uses thread-safe data structures (`ConcurrentHashMap`) to safely handle concurrent API requests in multi-threaded environments.

This prevents:
- Race conditions
- Token inconsistencies
- Concurrent access issues

under high request loads.

---

## Scalability Considerations

In production environments, the in-memory rate limiter can be extended using Redis or distributed caching systems.

This enables:

- Horizontal scaling
- Shared rate-limit state across instances
- Distributed traffic management
- Better fault tolerance
- API gateway integration

---

## API Endpoint

### Test Endpoint

```http
GET /api/request?userId=test
```

Example:

```http
http://localhost:8080/api/request?userId=test
```

---

## Possible Responses

### Allowed Request

```text
Request allowed
```

### Rate Limited

```text
Rate limit exceeded
```

---

## How to Run Locally

### Prerequisites

- Java 17 or higher
- IntelliJ IDEA (recommended)
- Maven

---

### Steps

1. Clone the repository

```bash
git clone <your-repository-url>
```

2. Open the project in IntelliJ IDEA

3. Run the Spring Boot application

4. Open browser or Postman:

```http
http://localhost:8080/api/request?userId=test
```

---

## Testing Burst Traffic

### PowerShell

```powershell
1..50 | % { curl http://localhost:8080/api/request?userId=test -UseBasicParsing }
```

This simulates high-frequency concurrent requests to test rate limiting behavior.

---

## Real-World Use Cases

- API Gateway protection
- Login brute-force prevention
- Payment API throttling
- Public REST API protection
- Microservice traffic shaping
- DDoS mitigation support

---

## Future Improvements

- Redis-backed distributed token storage
- Sliding Window algorithm support
- Dynamic user-based rate limits
- Prometheus + Grafana monitoring
- Kubernetes deployment support
- Distributed microservice integration

---

## Key Backend Engineering Concepts Demonstrated

- Rate limiting algorithms
- Concurrent programming
- REST API design
- Distributed systems thinking
- Traffic control strategies
- Backend reliability engineering
- Scalability design patterns

---

## Learning Outcome

This project demonstrates practical backend engineering concepts used in scalable distributed systems and large-scale API infrastructures.
