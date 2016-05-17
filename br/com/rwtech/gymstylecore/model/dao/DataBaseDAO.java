// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataBaseDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.pojo.DataBase;
import java.io.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            Gerador, Restaurador

public class DataBaseDAO
{

    public DataBaseDAO()
    {
        filefilter = new FileFilter() {

            public boolean accept(File file)
            {
                return file.getName().endsWith(".backup");
            }
        };
    }

    public Boolean gerarBackup()
    {
        Boolean result = Boolean.valueOf(Gerador.gerarBackup());
        while(processoRodando(DataBase.BACKUP).booleanValue()) ;
        return result;
    }

    public Boolean restore(String arquivo)
    {
        Boolean result = Restaurador.restore(arquivo);
        Restaurador.RESTAURANDO = Boolean.valueOf(true);
        while(processoRodando(DataBase.RESTORE).booleanValue()) ;
        Restaurador.RESTAURANDO = Boolean.valueOf(false);
        return result;
    }

    public Map readFiles()
    {
        File diretorio = new File(CAMINHO_BACKUP);
        verifyDirectory();
        File arquivos[] = diretorio.listFiles(filefilter);
        Map mapa = new TreeMap();
        for(int i = 0; i < arquivos.length; i++)
        {
            File file = arquivos[i];
            if(file.length() > 0L)
            {
                mapa.put(Long.valueOf(file.lastModified()), file);
                continue;
            }
            if(file.length() == 0L)
                delete(file.getName());
        }

        return mapa;
    }

    public static void verifyDirectory()
    {
        String caminho = System.getenv("windir");
        caminho = caminho.substring(0, caminho.indexOf("\\"));
        File diretorio = new File((new StringBuilder()).append(caminho).append(CAMINHO_BACKUP).toString());
        if(!diretorio.isDirectory())
            diretorio.mkdir();
    }

    public static Boolean delete(String arquivo)
    {
        String caminho = System.getenv("windir");
        caminho = caminho.substring(0, caminho.indexOf("\\"));
        String fileName = (new StringBuilder()).append(caminho).append(CAMINHO_BACKUP).append("\\").append(arquivo).toString();
        verifyDirectory();
        File f = new File(fileName);
        if(!f.exists())
        {
            return Boolean.valueOf(false);
        } else
        {
            boolean success = f.delete();
            return Boolean.valueOf(success);
        }
    }

    public static Boolean processoRodando(String comando)
    {
        Boolean tem = Boolean.valueOf(false);
        Scanner s = null;
        try
        {
            s = new Scanner(Runtime.getRuntime().exec("cmd /c tasklist").getInputStream());
        }
        catch(IOException ex) { }
        do
        {
            if(!s.hasNext())
                break;
            String processo = s.nextLine();
            if(processo.startsWith(comando))
                tem = Boolean.valueOf(true);
        } while(true);
        return tem;
    }

    public static String CAMINHO_BACKUP = "\\backupBancoGymStyle";
    private FileFilter filefilter;

}
