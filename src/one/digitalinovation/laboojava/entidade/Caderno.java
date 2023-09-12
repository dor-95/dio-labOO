package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materia;

public class Caderno extends Produto {

    private Materia materia;

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) + materia.getFator();
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "Código='" + getCodigo() + '\'' +
                ", Tipo=" + materia +
                ", Preço='" + getPreco() + '\'' +
                '}';
    }
}