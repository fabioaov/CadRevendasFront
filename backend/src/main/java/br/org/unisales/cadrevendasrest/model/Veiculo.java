package br.org.unisales.cadrevendasrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author andre luiz
 * @author fabio augusto
 * @author henrique de oliveira
 * @author welder souza
 * 
 * cria a classe de entidade que é uma tabela no banco de dados
 * tem sete campos:
 * id = chave primária
 * marca = coluna do tipo varchar
 * modelo = coluna do tipo varchar
 * ano = coluna do tipo int
 * renavam = coluna do tipo long
 * placa = coluna do tipo varchar
 * cor = coluna do tipo varchar
 * vendido = coluna do tipo boolean
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private Long renavam;
    private String placa;
    private String cor;
    private Boolean vendido;
}
