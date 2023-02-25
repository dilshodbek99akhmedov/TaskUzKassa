package uz.uzkassa.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
@ConditionalOnProperty(
        name = "springdoc.swagger-ui.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class OpenApiConfigurer {

    private static final String BEARER_FORMAT = "JWT";
    private static final String SCHEME = "Bearer";
    private static final String SECURITY_SCHEME = "Security Scheme";

    @Bean
    public OpenAPI api() {

        return new OpenAPI()
                .schemaRequirement(SECURITY_SCHEME, getSecurityScheme())
                .security(getSecurityRequirement());
    }

    private List<SecurityRequirement> getSecurityRequirement() {
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(SECURITY_SCHEME);
        return Collections.singletonList(securityRequirement);
    }

    private SecurityScheme getSecurityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.bearerFormat(BEARER_FORMAT)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme(SCHEME);
        return securityScheme;
    }

}
