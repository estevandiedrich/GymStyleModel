// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RespostaService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.RespostaDAO;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;

public class RespostaService
{

    public RespostaService()
    {
    }

    public Resposta readByDestino(Long id)
    {
        return DaoLocator.getRespostaDAO().readByDestino(id);
    }
}
