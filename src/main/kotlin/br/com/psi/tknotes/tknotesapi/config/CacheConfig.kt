package br.com.psi.tknotes.tknotesapi.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit


@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        return CaffeineCacheManager("notesInCache")
                .apply {
                    isAllowNullValues = false
                    setCaffeine(
                            Caffeine.newBuilder()
                                    .maximumSize(100)
                                    .expireAfterAccess(1, TimeUnit.HOURS)
                    )
                }
    }
}
