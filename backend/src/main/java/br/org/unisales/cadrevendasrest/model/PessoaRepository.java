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
 * traz métodos para inserir, excluir etc entidades do tipo Pessoa
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /**
     * procura pessoas com um determinado nome sem importar maiúsculas ou
     * minúsculas
     *
     * @param nome
     * @return
     */
    public List<Pessoa> findByNomeIgnoreCase(String nome);

    /**
     * pesquisa pessoas com um 'pedaço' do nome independente de maiúsculas ou
     * minúsculas, nesse caso usa-se uma Query com as funções UPPER - para
     * transformar em maiúscula - e CONCAT - para concatenar com os sinais de
     * '%'
     *
     * @param nome
     * @return
     */
    @Query("select P from Pessoa P where UPPER(P.nome) like UPPER(CONCAT('%', ?1, '%'))")
    public List<Pessoa> procuraPorNome(String nome);

    /**
     * faz a mesma coisa do método anterior mas agora pode receber ordenação
     *
     * @param nome
     * @param sort
     * @return
     */
    @Query("select P from Pessoa P where UPPER(P.nome) like UPPER(CONCAT('%', ?1, '%'))")
    public List<Pessoa> procuraPorNome(String nome, Sort sort);
}
