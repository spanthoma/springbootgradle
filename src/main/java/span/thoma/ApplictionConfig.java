package span.thoma;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by admin on 2017-09-20.
 */
@Configuration
public class ApplictionConfig {

    @Bean
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        String profile  = System.getProperty("spring.profiles.active", "");
        reloadableResourceBundleMessageSource.setBasename("classpath:message/message" + profile);
        return  reloadableResourceBundleMessageSource;

    }
}
