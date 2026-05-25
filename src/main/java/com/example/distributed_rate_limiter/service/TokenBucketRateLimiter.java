package com.example.distributed_rate_limiter.service;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {

    private static class Bucket {
        int tokens;
        long windowStart;
    }

    private final int capacity;
    private final long windowSizeMillis;
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, int windowSeconds) {
        this.capacity = capacity;
        this.windowSizeMillis = windowSeconds * 1000L;
    }

    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        Bucket bucket = buckets.computeIfAbsent(key, k -> {
            Bucket b = new Bucket();
            b.tokens = capacity;
            b.windowStart = now;
            return b;
        });

        if (now - bucket.windowStart >= windowSizeMillis) {
            bucket.tokens = capacity;
            bucket.windowStart = now;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }
        return false;
    }
}
