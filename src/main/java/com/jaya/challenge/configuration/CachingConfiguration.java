package com.jaya.challenge.configuration;

import com.github.benmanes.caffeine.cache.CaffeineSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Slf4j
public class CachingConfiguration extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        log.info("Creating CacheBuilder");
        CaffeineCacheManager manager = new CaffeineCacheManager();
        CaffeineSpec spec = CaffeineSpec.parse("expireAfterWrite=5m,maximumSize=100");
        manager.setCaffeineSpec(spec);
        return manager;
    }

}
