package com.example.distributed_rate_limiter.controller;

import com.example.distributed_rate_limiter.service.TokenBucketRateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    private final TokenBucketRateLimiter limiter =
            new TokenBucketRateLimiter(10, 1);

    @GetMapping("/api/request")
    public String handleRequest(@RequestParam String userId) {
        if (limiter.allowRequest(userId)) {
            return "Request allowed";
        }
        return "Rate limit exceeded";
    }
}
