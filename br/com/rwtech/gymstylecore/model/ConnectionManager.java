// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionManager.java

package br.com.rwtech.gymstylecore.model;

import java.sql.Connection;
import java.sql.DriverManager;

// Referenced classes of package br.com.rwtech.gymstylecore.model:
//            ConnectionDB

public class ConnectionManager extends ConnectionDB
{

    public static ConnectionManager getInstance()
    {
        if(instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    private ConnectionManager()
    {
        conn = null;
    }

    public Connection getConnection()
    {
        try
        {
            if(conn == null || conn.isClosed())
            {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            }
            if(!conn.getAutoCommit())
                conn.setAutoCommit(true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return conn;
    }

    public Connection getConnectionForTransaction()
    {
        try
        {
            if(conn == null || conn.isClosed())
            {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            }
            if(conn.getAutoCommit())
                conn.setAutoCommit(false);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return conn;
    }

    public static ConnectionManager instance = null;
    private Connection conn;

}
