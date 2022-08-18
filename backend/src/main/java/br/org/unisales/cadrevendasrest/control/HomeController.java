package br.org.unisales.cadrevendasrest.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre luiz
 * @author fabio augusto
 * @author henrique de oliveira
 * @author welder souza
 *
 */
@RestController
public class HomeController {

    /**
     * esse método marcado com requestmapping sem definição de verbo HTTP é
     * capaz de receber qualquer requisição (GET, POST etc.) e retorna uma
     * mensagem
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "API de revendas rodando";
    }
}
