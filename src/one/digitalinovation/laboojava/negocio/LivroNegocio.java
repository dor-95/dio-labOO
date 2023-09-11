package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Produto;

import java.util.Optional;

public class LivroNegocio {

    private Banco bancoDados;

    public LivroNegocio(Banco bancoDados) {
        this.bancoDados = bancoDados;
    }

    public Optional<Livro> consultar(String nome) {

        for (Produto produto : bancoDados.getProdutos()) {
            if (produto instanceof Livro livro) {
                if (livro.getNome().equalsIgnoreCase(nome)) {
                    return Optional.of(livro);
                }
            }
        }

        System.out.println("Livro não encontrado.");
        return Optional.empty();
    }
}
