// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseService.java

package br.com.rwtech.gymstylecore.model;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.rwtech.gymstylecore.model.pojo.Usuario;

// Referenced classes of package br.com.rwtech.gymstylecore.model:
//            ConnectionManager

public abstract class BaseService
{

    public BaseService()
    {
    }

    public void create(Object pojo)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            try
            {
                create(conn, pojo);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(SQLException ex)
        {
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Object pojo)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            update(conn, pojo);
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(Exception ex)
        {
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
            catch(Exception e1) { }
            ex.printStackTrace();
        }
        conn = null;
    }

    public Boolean delete(Long id)
    {
        Boolean retorno = Boolean.valueOf(false);
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            retorno = delete(conn, id);
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(Exception ex)
        {
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
        }
        conn = null;
        return retorno;
    }

    public Boolean disabled(Long id)
    {
        Connection conn = null;
        Boolean retorno = Boolean.valueOf(false);
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            if(disabled(conn, id).booleanValue())
                retorno = Boolean.valueOf(true);
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(Exception ex)
        {
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
        }
        conn = null;
        return retorno;
    }

    public void create(Connection connection, Object obj)
    {
    	
    }

    public void update(Connection connection, Object obj)
    {
    	
    }

    protected Boolean delete(Connection conn, Long id)
    {
        return Boolean.valueOf(false);
    }

    protected Boolean disabled(Connection conn, Long id)
    {
        return Boolean.valueOf(false);
    }

    public Map readList()
    {
        return null;
    }

    public abstract Object readById(Long long1);

    public abstract List readByCriteria(Map map);

    public Map paginator(Map input)
    {
        return null;
    }

    protected Boolean isUnique(Object pojo)
    {
        return Boolean.valueOf(true);
    }
}
