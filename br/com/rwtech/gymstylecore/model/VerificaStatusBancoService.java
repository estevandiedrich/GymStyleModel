// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerificaStatusBancoService.java

package br.com.rwtech.gymstylecore.model;

import gymstyledb.VerificaStatusBD;

// Referenced classes of package br.com.rwtech.gymstylecore.model:
//            ConnectionManager

public class VerificaStatusBancoService
{

    public VerificaStatusBancoService()
    {
    }

    public static Boolean analisar()
    {
        return VerificaStatusBD.getInstance().analisar(ConnectionManager.getInstance().getConnection());
    }
}
