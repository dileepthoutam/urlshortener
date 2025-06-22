package com.dileep.urlshortener;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class UrlController {

    @Autowired
    private UrlShorteningService shorteningService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String longUrl = body.get("url");
        if (longUrl == null || longUrl.isBlank()) {
            return ResponseEntity.badRequest().body("Missing or invalid URL.");
        }
        String shortCode = shorteningService.shorten(longUrl);
        return ResponseEntity.ok(getDomainName(request) + shortCode);
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode) {
        Optional<UrlMappingEntity> urlMap = shorteningService.resolve(shortCode);
        return urlMap
                .map(url -> ResponseEntity.status(302).header("Location", url.getLongUrl()).build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private String getDomainName(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();

        String domain = scheme + "://" + serverName + ((serverPort != 80 && serverPort != 443) ? ":" + serverPort : "");

        return domain + "/r/";
    }

}
