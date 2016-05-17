// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Permissao.java

package br.com.rwtech.gymstylecore.model.pojo;


public class Permissao
    implements Comparable
{

    public Permissao(String nome, String grupo)
    {
        this.nome = "";
        descricao = "";
        this.grupo = "";
        ativo = Boolean.valueOf(true);
        this.nome = nome;
        this.grupo = grupo;
    }

    public Permissao(String nome, String descricao, String grupo)
    {
        this.nome = "";
        this.descricao = "";
        this.grupo = "";
        ativo = Boolean.valueOf(true);
        this.nome = nome;
        this.descricao = descricao;
        this.grupo = grupo;
    }

    public Permissao(String nome, String descricao, String grupo, Boolean ativo)
    {
        this.nome = "";
        this.descricao = "";
        this.grupo = "";
        this.ativo = Boolean.valueOf(true);
        this.nome = nome;
        this.descricao = descricao;
        this.grupo = grupo;
        this.ativo = ativo;
    }

    public Permissao(String nome, Boolean ativo)
    {
        this.nome = "";
        descricao = "";
        grupo = "";
        this.ativo = Boolean.valueOf(true);
        this.nome = nome;
        this.ativo = ativo;
    }

    public Permissao()
    {
        nome = "";
        descricao = "";
        grupo = "";
        ativo = Boolean.valueOf(true);
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Permissao(String nome)
    {
        this.nome = "";
        descricao = "";
        grupo = "";
        ativo = Boolean.valueOf(true);
        this.nome = nome;
    }

    public String getGrupo()
    {
        return grupo;
    }

    public void setGrupo(String grupo)
    {
        this.grupo = grupo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }

    public String toString()
    {
        return descricao;
    }

    public int compareTo(Permissao o)
    {
        return nome.compareTo(o.nome);
    }

    public int compareTo(Object x0)
    {
        return compareTo((Permissao)x0);
    }

    private String nome;
    private String descricao;
    private String grupo;
    private Boolean ativo;
    public static String GRUPO_CADASTRO = "cadastro";
    public static String GRUPO_GERENCIAR = "gerenciar";
    public static String GRUPO_CONFIGURACAO = "configuracao";
    public static String GRUPO_RELATORIO = "relatorio";

}
