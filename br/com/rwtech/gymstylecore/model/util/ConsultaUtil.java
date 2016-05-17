// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConsultaUtil.java

package br.com.rwtech.gymstylecore.model.util;


public class ConsultaUtil
{

    public ConsultaUtil()
    {
    }

    public static String normalize(String str)
    {
        char chars[] = str.toCharArray();
        StringBuffer ret = new StringBuffer(chars.length * 2);
        for(int i = 0; i < chars.length; i++)
        {
            char aChar = chars[i];
            if(aChar == ' ' || aChar == '\t')
            {
                ret.append(' ');
                continue;
            }
            if(aChar <= ' ' || aChar >= '\u0100')
                continue;
            if(FIRST_CHAR[aChar - 32] != ' ')
                ret.append(FIRST_CHAR[aChar - 32]);
            if(SECOND_CHAR[aChar - 32] != ' ')
                ret.append(SECOND_CHAR[aChar - 32]);
        }

        return ret.toString();
    }

    public static String clearString(String name)
    {
        if(name != null)
        {
            name = name.replace(" ", "");
            name = name.replace(" ", "");
            name = name.replace(" ", "");
            name = name.replace(" ", "");
            name = name.replace(" ", "");
        }
        return name;
    }

    public static String getDataFormatBD(String criterioInicio)
    {
        criterioInicio = criterioInicio.replaceAll("_", "");
        String retorno = null;
        String data[] = criterioInicio.split("/");
        if(data.length == 3)
        {
            int dia = Integer.valueOf(data[0]).intValue();
            int mes = Integer.valueOf(data[1]).intValue();
            int ano = Integer.valueOf(data[2]).intValue();
            if(mes == 2)
            {
                if(dia > 28)
                    dia = 28;
            } else
            if(dia > 31)
                dia = 31;
            if(dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11))
                dia = 30;
            if(mes > 12)
                mes = 12;
            retorno = (new StringBuilder()).append(ano).append("-").append(mes >= 10 ? "" : "0").append(mes).append("-").append(dia >= 10 ? "" : "0").append(dia).toString();
            if(retorno.length() == 10)
                return retorno;
        }
        return null;
    }

    private static final char FIRST_CHAR[] = " !'#$%&'()*+\\-./0123456789:;<->?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ E ,f'.++^%S<O Z  ''''.--~Ts>o ZY !C#$Y|$'(a<--(_o+23'u .,1o>113?AAAAAAACEEEEIIIIDNOOOOOXOUUUUyTsaaaaaaaceeeeiiiidnooooo/ouuuuyty".toCharArray();
    private static final char SECOND_CHAR[] = "  '         ,                                               \\                                   $  r'. + o  E      ''    M  e     #  =  'C.<  R .-..     ..>424     E E               E E     hs    e e         h     e e     h ".toCharArray();

}
