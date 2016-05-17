// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PerfilAcesso.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.util.List;

// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class PerfilAcesso extends POJO
{

    public PerfilAcesso()
    {
    }

    public List<Faixa> getFaixas()
    {
        return faixas;
    }

    public void setFaixas(List<Faixa> faixas)
    {
        this.faixas = faixas;
    }

    public String getPerfilAcesso()
    {
        return perfilAcesso;
    }

    public void setPerfilAcesso(String perfilAcesso)
    {
        this.perfilAcesso = perfilAcesso;
    }

    public String toString()
    {
        return getPerfilAcesso();
    }

    private String perfilAcesso;
    private List<Faixa> faixas;
}
