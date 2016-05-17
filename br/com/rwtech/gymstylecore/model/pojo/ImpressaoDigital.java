// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImpressaoDigital.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO, Dedo

public class ImpressaoDigital extends POJO
{

    public ImpressaoDigital()
    {
    }

    public ImpressaoDigital(String primeiroTemplate, String segundoTemplate)
    {
        this.primeiroTemplate = primeiroTemplate;
        this.segundoTemplate = segundoTemplate;
    }

    public String getPrimeiroTemplate()
    {
        return primeiroTemplate;
    }

    public void setPrimeiroTemplate(String primeiroTemplate)
    {
        this.primeiroTemplate = primeiroTemplate;
    }

    public String getSegundoTemplate()
    {
        return segundoTemplate;
    }

    public void setSegundoTemplate(String segundoTemplate)
    {
        this.segundoTemplate = segundoTemplate;
    }

    public Dedo getDedo()
    {
        return dedo;
    }

    public void setDedo(Dedo dedo)
    {
        this.dedo = dedo;
    }

    public String getFormatoTemplateWeb()
    {
        String template = "";
        if(dedo != null && primeiroTemplate != null && segundoTemplate != null)
            template = (new StringBuilder()).append(dedo.getId()).append("|").append(primeiroTemplate).append("|").append(segundoTemplate).append("#").toString();
        return template;
    }

    private String primeiroTemplate;
    private String segundoTemplate;
    private Dedo dedo;
}
