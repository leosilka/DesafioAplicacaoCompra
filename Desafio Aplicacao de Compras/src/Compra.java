public class Compra implements Comparable<Compra>{
    private String descricao;
    private double valor;

    //Metodo constructor da classe compra
    public Compra(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    //Metodo getter para obter a descricao da compra
    public String getDescricao() {
        return descricao;
    }

    //Metodo getter para obter o valor da compra
    public double getValor() {
        return valor;
    }

    @Override
    //Representando em forma de string
    public String toString() {
        return "Compra: descricao = " + descricao + "valor = " + valor;
    }

    @Override
    //Metodo compareTo para ordenacao da lista
    public int compareTo(Compra outraCompra) {
        return Double.valueOf(this.valor).compareTo(Double.valueOf(outraCompra.valor));
    }
}
