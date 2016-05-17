// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileBackup.java

package br.com.rwtech.gymstylecore.model.pojo;

import java.io.File;

public class FileBackup
{

    public FileBackup(File file)
    {
        this.file = file;
        tamanho = file.length();
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public long getTamanho()
    {
        return tamanho;
    }

    private File file;
    private long tamanho;
}
