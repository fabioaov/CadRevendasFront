package br.org.unisales.cadrevendasrest.control;

import br.org.unisales.cadrevendasrest.model.Veiculo;
import br.org.unisales.cadrevendasrest.model.VeiculoRepository;
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
@RequestMapping("/veiculo")
public class VeiculoController {

    /*
    insere automaticamente um repositório de dados para veículos
     */
    @Autowired
    VeiculoRepository repository;

    /*
    método que retorna a listagem de veículos ordenada por marca
    atende no endpoint /veiculo com verbo GET
     */
    @GetMapping({"", "/"})
    public List<Veiculo> getVeiculos() {
        return repository.findAll(Sort.by("marca"));
    }

    /*
    método que retorna a listagem de veículos ordenada por marca
    fazendo uma pesquisa por parte da marca que está salva no banco de dados
    atende no endpoint /veiculo/pesquisa com verbo GET
    e necessita de um parâmetro chamado 'marca'
     */
    @GetMapping("/pesquisa")
    public List<Veiculo> getVeiculos(String marca) {
        return repository.procuraPorMarca(marca, Sort.by("marca"));
    }

    /*
    método que recebe um veículo enviado na requisição e o insere no banco de dados
    retorna após inserir já com o ID
    atende no endpoint /veiculo com verbo POST
    a anotação @RequestBody é importante pois indica que os dados da requisição 
    serão enviados no corpo da requisição (em JSON)
     */
    @PostMapping({"", "/"})
    public Veiculo insere(@RequestBody Veiculo veiculo) {
        return repository.save(veiculo);
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
    public Veiculo atualiza(@RequestBody Veiculo veiculo) {
        if (veiculo.getId() != null) {
            return repository.save(veiculo);
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
