package com.dileep.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepo extends JpaRepository<UrlMappingEntity, Long> {
    @Query("SELECT u FROM UrlMappingEntity u WHERE u.shortCode = :shortCode")
    public Optional<UrlMappingEntity> findByShortCode(String shortCode);

    @Query("SELECT u FROM UrlMappingEntity u WHERE u.longUrl = longUrl")
    public Optional<UrlMappingEntity> doesLongUrlExist(String longUrl);
}
