// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Restaurador.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionDB;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Map;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            DataBaseDAO, Gerador

public class Restaurador
{

    public Restaurador()
    {
    }

    public static Boolean restore(String arquivo)
    {
        Boolean retorno = Boolean.valueOf(false);
        if(arquivo != null && !arquivo.isEmpty() && arquivo.endsWith(".backup"))
        {
            HORA_BACKUP = (new StringBuilder()).append(CalendarUtil.getDataCurrente()).append("_").append(CalendarUtil.getHorMinSegCalendar(Calendar.getInstance())).toString();
            String dir = System.getenv("windir");
            dir = dir.substring(0, dir.indexOf("\\"));
            String arquivoBackup = (new StringBuilder()).append(dir).append(DataBaseDAO.CAMINHO_BACKUP).append("\\").append(arquivo).toString();
            try
            {
                String exe = Gerador.getCaminho();
                if(!exe.isEmpty())
                {
                    exe = exe.replaceAll("pg_dump.exe", "");
                    Process p = executeQuery(exe, "DROP SCHEMA IF EXISTS public CASCADE;");
                    p.waitFor();
                    p = executeQuery(exe, "CREATE SCHEMA public;");
                    p.waitFor();
                    RESTAURANDO = Boolean.valueOf(true);
                    ProcessBuilder pb = new ProcessBuilder(new String[] {
                        (new StringBuilder()).append(exe).append("/pg_restore.exe").toString(), "--host", "localhost", "--port", ConnectionDB.porta, "--username", ConnectionDB.user, "--dbname", ConnectionDB.name, "--no-password", 
                        "--verbose", "--schema", "public", arquivoBackup
                    });
                    pb.environment().put("PGPASSWORD", ConnectionDB.pass);
                    pb.redirectErrorStream(true);
                    p = pb.start();
                } else
                {
                    retorno = Boolean.valueOf(false);
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
                retorno = Boolean.valueOf(false);
            }
            retorno = Boolean.valueOf(true);
        } else
        {
            retorno = Boolean.valueOf(false);
        }
        return retorno;
    }

    public static Process executeQuery(String caminho, String sql)
    {
        Process p = null;
        try
        {
            ProcessBuilder pb = new ProcessBuilder(new String[] {
                (new StringBuilder()).append(caminho).append("/psql.exe").toString(), "--host", "localhost", "--port", ConnectionDB.porta, "--username", "postgres", "--dbname", ConnectionDB.name, "--no-password", 
                "-c", sql
            });
            pb.environment().put("PGPASSWORD", ConnectionDB.pass);
            pb.redirectErrorStream(true);
            p = pb.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return p;
    }

    public static String caminhoExePostgree(String caminho)
    {
        File file = null;
        String exe = "";
        file = new File(caminho);
        if(file != null && file.isDirectory())
        {
            exe = caminho;
            if(!(new File((new StringBuilder()).append(exe).append("/pg_restore.exe").toString())).canExecute())
                exe = "";
        }
        return exe;
    }

    private static String CAMINHO = "backup/";
    private static String NOME_BACKUP = "Academia.backup";
    private static String HORA_BACKUP = "";
    public static Boolean RESTAURANDO = Boolean.valueOf(false);

}
