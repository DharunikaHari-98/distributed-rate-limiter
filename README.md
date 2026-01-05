# Distributed Rate Limiter (Token Bucket Algorithm)

##  Overview
This project implements a **distributed-ready API rate limiter** using the **Token Bucket algorithm** with **Spring Boot**.  
It is designed to protect backend services from **burst traffic**, ensure **fair usage**, and improve **system reliability**.

The service enforces **per-user rate limits** and demonstrates how real-world systems handle **high-QPS traffic**, similar to architectures used in large-scale systems.

---

##  Why Rate Limiting?
Rate limiting is a critical component in backend systems to:
- Prevent API abuse and brute-force attacks
- Handle burst traffic safely
- Ensure fair resource allocation across users
- Protect downstream microservices
- Improve availability and reliability

This pattern is widely used in **FAANG-scale systems**.

---

##  Tech Stack
- Java
- Spring Boot
- REST APIs
- Token Bucket Algorithm
- Thread-safe data structures (ConcurrentHashMap)

---

##  High-Level Architecture
Client
↓
Spring Boot REST Controller
↓
Token Bucket Rate Limiter
↓
Protected Backend Service

Each user is assigned an independent token bucket.  
Requests are allowed only if tokens are available.

---

## 🚦 Rate Limiting Strategy
- **Algorithm**: Token Bucket
- **Burst Capacity**: 10 requests
- **Refill Rate**: 1 token per second
- **Scope**: Per user (based on `userId`)

This allows short bursts of traffic while enforcing long-term limits.

---

##  How to Run Locally

### Prerequisites
- Java 17 or higher
- IntelliJ IDEA (recommended)

### Steps
1. Clone or download the repository
2. Open the project in IntelliJ
3. Run the main class:

http://localhost:8080/api/request?userId=test
### Possible Responses
- `Request allowed`
- `Rate limit exceeded`

---

##  Testing Rate Limiting (Burst Traffic)

### PowerShell
```powershell
1..50 | % { curl http://localhost:8080/api/request?userId=test -UseBasicParsing }


