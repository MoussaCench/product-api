package sn.isi.l3gl.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "sn.isi.l3gl.core")
@EntityScan(basePackages = "sn.isi.l3gl.core.entity")
@EnableJpaRepositories(basePackages = "sn.isi.l3gl.core.repository")
public class ProductCoreAutoConfiguration {
}
