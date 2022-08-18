package br.org.unisales.cadrevendasrest.control;

import br.org.unisales.cadrevendasrest.model.Pessoa;
import br.org.unisales.cadrevendasrest.model.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/pessoa")
public class PessoaController {

    /*
    insere automaticamente um repositório de dados para veículos
     */
    @Autowired
    PessoaRepository repository;

    /*
    método que retorna a listagem de veículos ordenada por marca
    atende no endpoint /veiculo com verbo GET
     */
    @GetMapping({"", "/"})
    public List<Pessoa> getPessoas() {
        return repository.findAll(Sort.by("nome"));
    }

    /*
    método que retorna a listagem de veículos ordenada por marca
    fazendo uma pesquisa por parte da marca que está salva no banco de dados
    atende no endpoint /veiculo/pesquisa com verbo GET
    e necessita de um parâmetro chamado 'marca'
     */
    @GetMapping("/pesquisa")
    public List<Pessoa> getPessoas(String nome) {
        return repository.procuraPorNome(nome, Sort.by("nome"));
    }

    /*
    método que recebe um veículo enviado na requisição e o insere no banco de dados
    retorna após inserir já com o ID
    atende no endpoint /veiculo com verbo POST
    a anotação @RequestBody é importante pois indica que os dados da requisição 
    serão enviados no corpo da requisição (em JSON)
     */
    @PostMapping({"", "/"})
    public Pessoa insere(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    /*
    método que recebe um veículo enviada na requisição (com id preenchido)
    e o atualiza no banco de dados
    retorna o veículo atualizado
    caso não tenha id na requisição retorna null
    atende no endpoint /veiculo com verbo PUT
    a anotação @RequestBody é importante pois indica que os dados da requisição 
    serão enviados no corpo da requisição (em JSON)
     */
    @PutMapping({"", "/"})
    public Pessoa atualiza(@RequestBody Pessoa pessoa) {
        if (pessoa.getId() != null) {
            return repository.save(pessoa);
        }
        return null;
    }

    /*
    método que recebe um id de veículo enviado na requisição 
    caso tenha enviado o id, é excluído no banco de dados
    retorna uma mensagem
    o id é passado no path (caminho da url) por isso
    se usa o @PathVariable indicativo
     */
    @DeleteMapping("/{id}")
    public String exclui(@PathVariable("id") Long id) {
        if (id != null) {
            repository.deleteById(id);
            return "Excluído";
        }
        return null;
    }

}
