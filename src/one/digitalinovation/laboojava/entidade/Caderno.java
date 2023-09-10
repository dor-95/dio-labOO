package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materias;

public class Caderno extends Produto {

    private Materias materias;

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calcularFrete() {
        return 0;
    }

    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "Código='" + getCodigo() + '\'' +
                ", Tipo=" + materias +
                ", Preço='" + getPreco() + '\'' +
                '}';
    }
}