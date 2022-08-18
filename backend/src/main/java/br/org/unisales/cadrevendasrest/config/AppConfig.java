package br.org.unisales.cadrevendasrest.config;

import br.org.unisales.cadrevendasrest.filter.AutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author andre luiz
 * @author fabio augusto
 * @author henrique de oliveira
 * @author welder souza
 *
 */
@Configuration
public class AppConfig {

    //injetamos um filtro de autenticações
    @Autowired
    private AutFilter autFilter;

    /**
     * adicionamos o filtro de login na navegação
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<AutFilter> filterRegistrationBean() {
        FilterRegistrationBean<AutFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(autFilter);
        //aplica-se apenas ao endpoint veiculo
        registrationBean.addUrlPatterns("/veiculo/*");
        //define a ordem de precedencia do filtro
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
