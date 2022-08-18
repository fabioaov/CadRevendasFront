package br.org.unisales.cadrevendasrest.model;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre luiz
 * @author fabio augusto
 * @author henrique de oliveira
 * @author welder souza
 *
 * essa classe usa o mecanismo de ferramentas do Spring para prover uma forma
 * fácil de interagir com dados da nossa aplicação
 *
 * a anotação Repository insere uma classe anônima gerenciada pela aplicação que
 * traz métodos para inserir, excluir etc entidades do tipo Veiculo
 */
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    /**
     * procura veículos com uma determinada marca sem importar maiúsculas ou
     * minúsculas
     *
     * @param marca
     * @return
     */
    public List<Veiculo> findByMarcaIgnoreCase(String marca);

    /**
     * pesquisa veículos com um 'pedaço' da marca independente de maiúsculas ou
     * minúsculas nesse caso usa-se uma Query com as funções UPPER - para
     * transformar em maiúscula - e CONCAT - para concatenar com os sinais de
     * '%'
     *
     * @param marca
     * @return
     */
    @Query("select V from Veiculo V where UPPER(V.marca) like UPPER(CONCAT('%', ?1, '%'))")
    public List<Veiculo> procuraPorMarca(String marca);

    /**
     * faz a mesma coisa do método anterior mas agora pode receber ordenação
     *
     * @param marca
     * @param sort
     * @return
     */
    @Query("select V from Veiculo V where UPPER(V.marca) like UPPER(CONCAT('%', ?1, '%'))")
    public List<Veiculo> procuraPorMarca(String marca, Sort sort);
}
