// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataBaseService.java

package br.com.rwtech.gymstylecore.model.service;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.dao.DataBaseDAO;
import java.util.Map;

public class DataBaseService
{

    public DataBaseService()
    {
    }

    public Boolean gerarBackup()
    {
        return DaoLocator.getDataBaseDAO().gerarBackup();
    }

    public static Boolean processoRodando(String comando)
    {
        DaoLocator.getDataBaseDAO();
        return DataBaseDAO.processoRodando(comando);
    }

    public Boolean restore(String arquivo)
    {
        return DaoLocator.getDataBaseDAO().restore(arquivo);
    }

    public Boolean delete(String arquivo)
    {
        DaoLocator.getDataBaseDAO();
        return DataBaseDAO.delete(arquivo);
    }

    public Map readFiles()
    {
        return DaoLocator.getDataBaseDAO().readFiles();
    }
}
