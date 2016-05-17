// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Dedo.java

package br.com.rwtech.gymstylecore.model.pojo;


// Referenced classes of package br.com.rwtech.gymstylecore.model.pojo:
//            POJO

public class Dedo extends POJO
{

    public String getDedo()
    {
        return dedo;
    }

    public Dedo()
    {
    }

    public Dedo(int i)
    {
        switch(i)
        {
        case 1: // '\001'
            dedo = "Polegar Direito";
            break;

        case 2: // '\002'
            dedo = "Indicador Direito";
            break;

        case 3: // '\003'
            dedo = "M\351dio Direito";
            break;

        case 4: // '\004'
            dedo = "Anelar Direito";
            break;

        case 5: // '\005'
            dedo = "M\355nimo Direito";
            break;

        case 6: // '\006'
            dedo = "Polegar Esquerdo";
            break;

        case 7: // '\007'
            dedo = "Indicador Esquerdo";
            break;

        case 8: // '\b'
            dedo = "M\351dio Esquerdo";
            break;

        case 9: // '\t'
            dedo = "Anelar Esquerdo";
            break;

        case 10: // '\n'
            dedo = "M\355nimo Esquerdo";
            break;
        }
        setId(new Long(i));
    }

    public static int getValorDedo(String dedo)
    {
        if(dedo.equalsIgnoreCase("Polegar Direito"))
            return 1;
        if(dedo.equalsIgnoreCase("Indicador Direito"))
            return 2;
        if(dedo.equalsIgnoreCase("M\351dio Direito"))
            return 3;
        if(dedo.equalsIgnoreCase("Anelar Direito"))
            return 4;
        if(dedo.equalsIgnoreCase("M\355nimo Direito"))
            return 5;
        if(dedo.equalsIgnoreCase("Polegar Esquerdo"))
            return 6;
        if(dedo.equalsIgnoreCase("Indicador Esquerdo"))
            return 7;
        if(dedo.equalsIgnoreCase("M\351dio Esquerdo"))
            return 8;
        if(dedo.equalsIgnoreCase("Anelar Esquerdo"))
            return 9;
        return !dedo.equalsIgnoreCase("M\355nimo Esquerdo") ? 0 : 10;
    }

    public void setDedo(String dedo)
    {
        this.dedo = dedo;
    }

    public String toString()
    {
        return dedo.toString();
    }

    private String dedo;
}
