package com.wednesday.yber.config;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageRequest;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JOpenCageConfig {

    @Value("${geocoding.api.key}")
    private String OPENCAGEKEY;

    @Bean
    public JOpenCageGeocoder jOpenCageGeocoder(){
        return  new JOpenCageGeocoder(OPENCAGEKEY);
    }


}
