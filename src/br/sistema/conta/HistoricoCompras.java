package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;

public class HistoricoCompras {
    private List<Compra> compras = new ArrayList<>();    
    
    public HistoricoCompras()
    {
    	this.compras = new ArrayList<Compra>();
    }
    
    public List<Compra> getCompras() {
        return compras;
    }

    
    public void registrarCompra(Compra compra) {
        compras.add(compra);
    }

    public List<Compra> listarCompras() {
        return compras;
    }

    public Compra buscarPorCodigo(int codigo) {
        for (Compra c : compras) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    public List<Compra> buscarPorData(String data) {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getData().toString().equals(data)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
