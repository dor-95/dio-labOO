package one.digitalinovation.laboojava.entidade.constantes;

public enum Materia {
    M2(2),
    M5(5),
    M10(10);

    private double fator;

    /**
     * Contructor
     * @param fator Valor por tipo que influencia no cálculo do frete.
     */
    Materia(double fator) {this.fator = fator / 10;}

    public double getFator() {return fator;}
}
