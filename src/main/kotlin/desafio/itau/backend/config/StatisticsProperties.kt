package desafio.itau.backend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "statistics")
class StatisticsProperties {
    var windowOnSeconds: Long = 60
}
