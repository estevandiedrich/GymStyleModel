// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exercicio.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, GrupoMuscular, Aparelho

public class Exercicio extends POJO
{

    public Exercicio()
    {
    }

    public Aparelho getAparelho()
    {
        return aparelho;
    }

    public void setAparelho(Aparelho aparelho)
    {
        this.aparelho = aparelho;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getExercicio()
    {
        return exercicio;
    }

    public void setExercicio(String exercicio)
    {
        this.exercicio = exercicio;
    }

    public GrupoMuscular getGrupoMuscular()
    {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular)
    {
        this.grupoMuscular = grupoMuscular;
    }

    public String toString()
    {
        String str = "";
        if(exercicio != null)
            str = (new StringBuilder()).append(str).append(exercicio).toString();
        if(grupoMuscular != null)
            str = (new StringBuilder()).append(str).append(" - ").append(grupoMuscular.getGrupoMuscular()).toString();
        if(aparelho != null)
            str = (new StringBuilder()).append(str).append(" - ").append(aparelho.getAparelho()).toString();
        return str;
    }

    private String exercicio;
    private String descricao;
    private GrupoMuscular grupoMuscular;
    private Aparelho aparelho;
}
