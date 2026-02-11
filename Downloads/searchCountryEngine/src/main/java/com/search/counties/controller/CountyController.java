package com.search.counties.controller;

import com.search.counties.dto.CountyResponseDto;
import com.search.counties.services.CountyService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/suggest")
public class CountyController {

    private final CountyService service;
    private final Bucket bucket;

    public CountyController(CountyService service) {
        this.service = service;
        // Ratelimit: 30 hit per 1 minute allowed only
        Bandwidth limit = Bandwidth.classic(60, Refill.intervally(60, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<CountyResponseDto>> suggest(@RequestParam("q") String q) {
        // Try to consume 1 token before proceeding
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(service.suggest(q));
        }

        // Return 429 Too Many Requests if limit is exceeded
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
