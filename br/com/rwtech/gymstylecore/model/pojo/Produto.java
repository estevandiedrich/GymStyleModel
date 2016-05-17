// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Produto.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Categoria

public class Produto extends POJO
{

    public Produto()
    {
    }

    public Boolean getAlertaEstoqueMinimo()
    {
        return alertaEstoqueMinimo;
    }

    public void setAlertaEstoqueMinimo(Boolean alertaEstoqueMinimo)
    {
        this.alertaEstoqueMinimo = alertaEstoqueMinimo;
    }

    public byte[] getFoto()
    {
        return foto;
    }

    public void setFoto(byte foto[])
    {
        this.foto = foto;
    }

    public String getCodigoBarras()
    {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras)
    {
        this.codigoBarras = codigoBarras;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Double getPrecoCusto()
    {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto)
    {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda()
    {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda)
    {
        this.precoVenda = precoVenda;
    }

    public int getEstoqueMinimo()
    {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo)
    {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getEstoqueAtual()
    {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual)
    {
        this.estoqueAtual = estoqueAtual;
    }

    public Boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    private Integer codigo;
    private String nome;
    private Double precoCusto;
    private Double precoVenda;
    private int estoqueMinimo;
    private int estoqueAtual;
    private Boolean ativo;
    private String observacao;
    private Boolean alertaEstoqueMinimo;
    private byte foto[];
    private String codigoBarras;
    private Categoria categoria;
}
