package com.dileep.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UrlShorteningService {
    private final AtomicInteger counter = new AtomicInteger(1);

    private final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private UrlMappingRepo urlMappingRepo;

    public String shorten(String longUrl) {
        Optional<UrlMappingEntity> urlMap = urlMappingRepo.doesLongUrlExist(longUrl);
        if (urlMap.isPresent()) {
            return urlMap.get().getShortCode();
        }
        String shortCode = encode(counter.getAndIncrement());
        UrlMappingEntity urlMapping = new UrlMappingEntity();
        urlMapping.setShortCode(shortCode);
        urlMapping.setLongUrl(longUrl);
        urlMappingRepo.save(urlMapping);
        return shortCode;
    }

    public Optional<UrlMappingEntity> resolve(String shortCode) {
        return urlMappingRepo.findByShortCode(shortCode);
    }

    private String encode(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(BASE62.charAt(n % 62));
            n /= 62;
        }
        return sb.reverse().toString();
    }
}
