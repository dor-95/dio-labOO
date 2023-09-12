package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.constantes.Materia;

public class CadernoNegocio {

    private Banco bancoDados;

    public CadernoNegocio(Banco bancoDados) {
        this.bancoDados = bancoDados;
    }

    public void consultar(String materia) {

        int resultados = 0;

        for (Produto produto : bancoDados.getProdutos()) {
            if (produto instanceof Caderno caderno) {
                if (caderno.getMateria().equals(Materia.valueOf(materia.toUpperCase()))) {
                    System.out.println(caderno);
                    resultados++;
                }
            }
        }

        if (resultados == 0) {
            System.out.println("Nenhum caderno encontrado");
        }
    }
}
