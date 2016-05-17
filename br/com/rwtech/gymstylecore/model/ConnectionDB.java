// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionDB.java

package br.com.rwtech.gymstylecore.model;


public class ConnectionDB
{

    public ConnectionDB()
    {
        url = "";
        driver = "";
        caminho = "localhost:";
        url = (new StringBuilder()).append("jdbc:postgresql://").append(caminho).append(porta).append("/").append(name).append("/").toString();
        user = "postgres";
        pass = "postgres";
        driver = "org.postgresql.Driver";
    }

    public static ConnectionDB instance = null;
    protected String url;
    public static String pass = "";
    public static String user = "";
    protected String driver;
    protected String caminho;
    public static String name = "GymStyleDB";
    public static String porta = "5432";
    public static String VERSAO = "V1-72";

}
