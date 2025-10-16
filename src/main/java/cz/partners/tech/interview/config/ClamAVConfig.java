package cz.partners.tech.interview.config;

import cz.partners.tech.interview.config.data.ClamAVConfigData;
import fi.solita.clamav.ClamAVClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ClamAVConfig {

    private final ClamAVConfigData clamAVConfigData;

    @Bean
    public ClamAVClient clamavClient() {
        return new ClamAVClient(clamAVConfigData.getHost(), clamAVConfigData.getPort(), clamAVConfigData.getTimeout());
    }
}
