// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Gerador.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionDB;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            DataBaseDAO

public class Gerador
{

    public Gerador()
    {
    }

    public static String getCaminho()
    {
        String caminho = System.getenv("systemdrive");
        caminho = (new StringBuilder()).append(caminho).append("/ProgramGymStyle").toString();
        String exe = caminhoExePostgree((new StringBuilder()).append(caminho).append("/PostgreSQL/9.2/bin").toString());
        if(exe.isEmpty())
            exe = caminhoExePostgree((new StringBuilder()).append(caminho).append("/PostgreSQL/9.1/bin").toString());
        if(exe.isEmpty())
        {
            caminho = "C:\\Program Files";
            exe = caminhoExePostgree((new StringBuilder()).append(caminho).append("/PostgreSQL/9.1/bin").toString());
        }
        if(exe.isEmpty())
            exe = caminhoExePostgree((new StringBuilder()).append(caminho).append(" (x86)/PostgreSQL/9.1/bin").toString());
        if(exe.isEmpty())
            exe = caminhoExePostgree((new StringBuilder()).append(caminho).append("/PostgreSQL/9.2/bin").toString());
        if(exe.isEmpty())
            exe = caminhoExePostgree((new StringBuilder()).append(caminho).append(" (x86)/PostgreSQL/9.2/bin").toString());
        return exe;
    }

    public static boolean gerarBackup()
    {
        String dir = System.getenv("windir");
        dir = dir.substring(0, dir.indexOf("\\"));
        DataBaseDAO.verifyDirectory();
        HORA_BACKUP = (new StringBuilder()).append(CalendarUtil.getDataCurrente("-")).append("_").append(CalendarUtil.getHorMinSegCalendarHMS()).toString();
        String localBackup = (new StringBuilder()).append(dir).append(DataBaseDAO.CAMINHO_BACKUP).append("\\").append(HORA_BACKUP).append(NOME_BACKUP).toString();
        try
        {
            String exe = getCaminho();
            if(!exe.isEmpty())
            {
                ProcessBuilder pb = new ProcessBuilder(new String[] {
                    exe, "-i", "-h", "localhost", "-p", ConnectionDB.porta, "-U", ConnectionDB.user, "-F", "c", 
                    "-b", "-v", "-f", localBackup, ConnectionDB.name
                });
                pb.environment().put("PGPASSWORD", ConnectionDB.pass);
                pb.redirectErrorStream(true);
                Process p = pb.start();
            } else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static String caminhoExePostgree(String caminho)
    {
        File file = null;
        String exe = "";
        file = new File(caminho);
        if(file != null && file.isDirectory())
        {
            exe = (new StringBuilder()).append(caminho).append("/pg_dump.exe").toString();
            if(!(new File(exe)).canExecute())
                exe = "";
        }
        return exe;
    }

    private static String CAMINHO = "backupBancoGymStyle/";
    private static String NOME_BACKUP;
    private static String HORA_BACKUP = "";

    static 
    {
        NOME_BACKUP = (new StringBuilder()).append("GymStyle").append(ConnectionDB.VERSAO).append(".backup").toString();
    }
}
