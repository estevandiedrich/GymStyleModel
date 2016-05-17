// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serie.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Exercicio

public class Serie extends POJO
    implements Comparable
{

    public Serie()
    {
    }

    public String getSerie()
    {
        return serie;
    }

    public void setSerie(String serie)
    {
        this.serie = serie;
    }

    public String getRepeticao()
    {
        return repeticao;
    }

    public void setRepeticao(String repeticao)
    {
        this.repeticao = repeticao;
    }

    public String getCarga()
    {
        return carga;
    }

    public void setCarga(String carga)
    {
        this.carga = carga;
    }

    public Integer getOrdem()
    {
        if(ordem == null)
            ordem = new Integer(0);
        return ordem;
    }

    public void setOrdem(Integer ordem)
    {
        this.ordem = ordem;
    }

    public Exercicio getExercicio()
    {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio)
    {
        this.exercicio = exercicio;
    }

    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + (serie == null ? 0 : serie.hashCode());
        hash = 97 * hash + (repeticao == null ? 0 : repeticao.hashCode());
        hash = 97 * hash + (carga == null ? 0 : carga.hashCode());
        hash = 97 * hash + (exercicio == null ? 0 : exercicio.hashCode());
        return hash;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Serie other = (Serie)obj;
        if(serie != other.serie && (serie == null || !serie.equals(other.serie)))
            return false;
        if(repeticao != other.repeticao && (repeticao == null || !repeticao.equals(other.repeticao)))
            return false;
        if(carga != other.carga && (carga == null || !carga.equals(other.carga)))
            return false;
        return exercicio == other.exercicio || exercicio != null && exercicio.equals(other.exercicio);
    }

    public int compareTo(Serie o)
    {
        if(getOrdem().intValue() < o.getOrdem().intValue())
            return -1;
        return getOrdem() != o.getOrdem() ? 1 : 0;
    }

    public int compareTo(Object x0)
    {
        return compareTo((Serie)x0);
    }

    private String serie;
    private String repeticao;
    private String carga;
    private Integer ordem;
    private Exercicio exercicio;
}
