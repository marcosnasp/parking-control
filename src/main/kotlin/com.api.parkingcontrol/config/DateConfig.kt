import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.format.DateTimeFormatter

@Configuration
class DateConfig {

    companion object {
        const val DATETIME_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val LOCAL_DATETIME_SERIALIZER: LocalDateTimeSerializer = LocalDateTimeSerializer(
            DateTimeFormatter.ofPattern(Companion.DATETIME_FORMAT)
        )
    }

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        val module: JavaTimeModule = JavaTimeModule()
        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        return ObjectMapper().registerModule(module);
    }
}