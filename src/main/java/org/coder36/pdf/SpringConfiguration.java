package org.coder36.pdf;

import org.coder36.pdf.service.StudentRepository;
import org.coder36.pdf.servlet.PDFServlet;
import org.coder36.pdf.servlet.QRImageServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "org.coder36")
public class SpringConfiguration {

    @Autowired
    private QRImageServlet qrImageServlet;

    @Bean
    public ServletRegistrationBean qrServlet() {
        return new ServletRegistrationBean( qrImageServlet, "/qr.png" );
    }

    @Bean
    public ServletRegistrationBean pdfServlet() {
        return new ServletRegistrationBean( new PDFServlet(), "/form.pdf" );
    }

    @Bean
    public ServletRegistrationBean jsfServlet() {
        return new ServletRegistrationBean( new javax.faces.webapp.FacesServlet(), "*.jsf" );
    }

}
