// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionManagerLog.java

package br.com.rwtech.gymstylecore.model;

import java.sql.Connection;
import java.sql.DriverManager;

// Referenced classes of package br.com.rwtech.gymstylecore.model:
//            ConnectionDB

public class ConnectionManagerLog extends ConnectionDB
{

    public static ConnectionManagerLog getInstance()
    {
        if(instance == null)
            instance = new ConnectionManagerLog();
        return instance;
    }

    private ConnectionManagerLog()
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

    public static ConnectionManagerLog instance = null;
    private Connection conn;

}
