// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Logs.java

package br.com.rwtech.gymstylecore.model.util;

import java.io.*;

public class Logs
{

    public Logs()
    {
    }

    public static void escrever(String texto)
    {
        File arquivo = new File("C:\\arquivo.txt");
        try
        {
            if(!arquivo.exists())
                arquivo.createNewFile();
            File arquivos[] = arquivo.listFiles();
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.newLine();
            bw.close();
            fw.close();
        }
        catch(Exception e) { }
    }
}
