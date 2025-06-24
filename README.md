# URL Shortener Application

A simple URL shortening service built with Java, Spring Boot, and Thymeleaf.  
It encodes long URLs into short, easy-to-share links using **BASE62 encoding**.

---

## Features

- Convert long URLs into short BASE62-encoded strings
- Responsive UI built with Thymeleaf and Bootstrap
- AJAX-powered form for seamless URL shortening without page reload
- REST API endpoints to create and resolve shortened URLs
- Redirects from short URLs to the original long URLs

---

## Tech Stack

- Java 17+
- Spring Boot (Web, Data JPA)
- Thymeleaf for server-side templating
- Bootstrap 5 for UI styling
- BASE62 encoding for URL shortening logic
- Configurable datasource (I've used MySQL. You should probably install the appropriate Driver.)

---

## Getting Started

### Prerequisites

- Java 17 or higher installed
- Maven or Gradle build tool
- Database (default is H2 in-memory for quick start)

### Build and Run

```bash
# Clone the repo
git clone https://github.com/yourusername/url-shortener.git
cd url-shortener

# Build with Maven
./mvnw clean install

# Run the app
./mvnw spring-boot:run
```

## Future Improvements

- Improve the BASE62 encoding algorithm to better generate URLs of consisten size
- Implement URL expiration and analytics tracking.
- Add user authentication and URL management dashboard.
- Support bulk URL shortening and QR code generation.
