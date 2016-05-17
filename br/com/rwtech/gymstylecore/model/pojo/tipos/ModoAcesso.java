// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModoAcesso.java

package br.com.rwtech.gymstylecore.model.pojo.tipos;

import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;

public enum ModoAcesso
{
    CONTROLADO_CONTROLADO,
    CONTROLADO_LIBERADO,
    CONTROLADO_BLOQUEADO,
    BLOQUEADO_CONTROLADO,
    BLOQUEADO_LIBERADO,
    BLOQUEADO_BLOQUEADO,
    LIBERADO_CONTROLADO,
    LIBERADO_LIBERADO,
    LIBERADO_BLOQUEADO;

    public static ModoAcesso getTipo(int val)
    {
        switch(val)
        {
        case 1: // '\001'
            return CONTROLADO_CONTROLADO;

        case 2: // '\002'
            return CONTROLADO_LIBERADO;

        case 3: // '\003'
            return CONTROLADO_BLOQUEADO;

        case 4: // '\004'
            return BLOQUEADO_CONTROLADO;

        case 5: // '\005'
            return BLOQUEADO_LIBERADO;

        case 6: // '\006'
            return BLOQUEADO_BLOQUEADO;

        case 7: // '\007'
            return LIBERADO_CONTROLADO;

        case 8: // '\b'
            return LIBERADO_LIBERADO;

        case 9: // '\t'
            return LIBERADO_BLOQUEADO;
        }
        return CONTROLADO_CONTROLADO;
    }

    public static void setRequisicaoByParametro(Dispositivo dis, Long num)
    {
        dis.setEntradaDirEsq(Integer.valueOf(0));
        dis.setImprime(Boolean.FALSE);
        dis.setModoAcesso(BLOQUEADO_BLOQUEADO);
        String valor = Integer.toBinaryString(num.intValue());
        for(int i = valor.length(); i <= 7; i++)
            valor = (new StringBuilder()).append("0").append(valor).toString();

        String entrada = valor.substring(0, 2);
        String imprime = valor.substring(2, 4);
        String modoAcesso = valor.substring(4, 8);
        if(imprime.equalsIgnoreCase("01"))
            dis.setImprime(Boolean.TRUE);
        if(entrada.equalsIgnoreCase("01"))
            dis.setEntradaDirEsq(Integer.valueOf(1));
        if(modoAcesso.equalsIgnoreCase("0000"))
            dis.setModoAcesso(CONTROLADO_CONTROLADO);
        if(modoAcesso.equalsIgnoreCase("0001"))
            dis.setModoAcesso(CONTROLADO_LIBERADO);
        if(modoAcesso.equalsIgnoreCase("0010"))
            dis.setModoAcesso(CONTROLADO_BLOQUEADO);
        if(modoAcesso.equalsIgnoreCase("0011"))
            dis.setModoAcesso(LIBERADO_CONTROLADO);
        if(modoAcesso.equalsIgnoreCase("0100"))
            dis.setModoAcesso(LIBERADO_LIBERADO);
        if(modoAcesso.equalsIgnoreCase("0101"))
            dis.setModoAcesso(LIBERADO_BLOQUEADO);
        if(modoAcesso.equalsIgnoreCase("0110"))
            dis.setModoAcesso(BLOQUEADO_CONTROLADO);
        if(modoAcesso.equalsIgnoreCase("0111"))
            dis.setModoAcesso(BLOQUEADO_LIBERADO);
        if(modoAcesso.equalsIgnoreCase("1000"))
            dis.setModoAcesso(BLOQUEADO_BLOQUEADO);
    }

    public int getValor()
    {
        return getValor(this);
    }

    public static int getValor(ModoAcesso tipo)
    {
        if(tipo == CONTROLADO_CONTROLADO)
            return 1;
        if(tipo == CONTROLADO_LIBERADO)
            return 2;
        if(tipo == CONTROLADO_BLOQUEADO)
            return 3;
        if(tipo == BLOQUEADO_CONTROLADO)
            return 4;
        if(tipo == BLOQUEADO_LIBERADO)
            return 5;
        if(tipo == BLOQUEADO_BLOQUEADO)
            return 6;
        if(tipo == LIBERADO_CONTROLADO)
            return 7;
        if(tipo == LIBERADO_LIBERADO)
            return 8;
        return tipo != LIBERADO_BLOQUEADO ? 1 : 9;
    }

    public static int getValorToRequisicao(ModoAcesso tipo)
    {
        if(tipo == CONTROLADO_CONTROLADO)
            return 0;
        if(tipo == CONTROLADO_LIBERADO)
            return 1;
        if(tipo == CONTROLADO_BLOQUEADO)
            return 2;
        if(tipo == BLOQUEADO_CONTROLADO)
            return 6;
        if(tipo == BLOQUEADO_LIBERADO)
            return 7;
        if(tipo == BLOQUEADO_BLOQUEADO)
            return 8;
        if(tipo == LIBERADO_CONTROLADO)
            return 3;
        if(tipo == LIBERADO_LIBERADO)
            return 4;
        return tipo != LIBERADO_BLOQUEADO ? 0 : 5;
    }

    public static Long getValorToRequisicao(Boolean imprime, Boolean entradaHorario, Integer modoAcesso)
    {
        String binario = "0000";
        if(imprime.booleanValue() && entradaHorario.booleanValue())
            binario = "0101";
        else
        if(!imprime.booleanValue() && entradaHorario.booleanValue())
            binario = "0001";
        else
        if(imprime.booleanValue() && !entradaHorario.booleanValue())
            binario = "0100";
        String numUm = String.valueOf(Integer.parseInt(binario, 2));
        String numDois = String.valueOf(modoAcesso);
        String val = (new StringBuilder()).append(numUm).append("").append(numDois).toString();
        Long valor = new Long(Integer.parseInt(val, 16));
        return valor;
    }
}
