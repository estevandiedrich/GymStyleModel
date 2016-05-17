// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImpressaoDigitalDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Dedo;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            DedoDAO

public class ImpressaoDigitalDAO extends BaseDAO
{

    public ImpressaoDigitalDAO()
    {
    }

    public boolean create(List list, Long idUsuario)
    {
        Connection conn = ConnectionManager.getInstance().getConnectionForTransaction();
        try
        {
            ImpressaoDigital impDig;
            for(Iterator i$ = list.iterator(); i$.hasNext(); create(conn, impDig, idUsuario))
                impDig = (ImpressaoDigital)i$.next();

            conn.commit();
            return true;
        }
        catch(SQLException ex) { }
        try
        {
            conn.rollback();
        }
        catch(SQLException ex1)
        {
            Logger.getLogger(ImpressaoDigitalDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return false;
    }

    public void create(Connection conn, ImpressaoDigital pojo, Long idUsuario)
    {
        try
        {
            String sql = " INSERT INTO digitais(template1, template2, id_dedos_fk, id_usuarios_fk) VALUES (?, ?, ?, ?);";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setString(++i, pojo.getPrimeiroTemplate());
            pstm.setString(++i, pojo.getSegundoTemplate());
            pstm.setLong(++i, pojo.getDedo().getId().longValue());
            pstm.setLong(++i, idUsuario.longValue());
            pstm.execute();
            pstm.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public String getNameTable()
    {
        return "digitais";
    }
    @Override
    public String getOrderCampo()
    {
        return "id_dedos_fk";
    }
    @Override
    public ImpressaoDigital extract(ResultSet rs)
        throws Exception
    {
        ImpressaoDigital pojo = null;
        if(rs != null)
        {
            pojo = new ImpressaoDigital();
            pojo.setId(rsGetId(rs));
            pojo.setPrimeiroTemplate(rs.getString("template1"));
            pojo.setSegundoTemplate(rs.getString("template2"));
            pojo.setDedo(DaoLocator.getDedoDAO().extract(rs));
        }
        return pojo;
    }
    @Override
    public List readByCriteria(Map input)
    {
        List lista = new ArrayList();
        List listaCriterios = getFiltros(input);
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = " select * from digitais as digitais left join dedos as dedos on dedos.id_dedos = digitais.id_dedos_fk where true";
            for(int i = 0; i < listaCriterios.size(); i++)
                sql = (new StringBuilder()).append(sql).append(" and (").append((String)listaCriterios.get(i)).append(")").toString();

            sql = (new StringBuilder()).append(sql).append(" order by ").append(ORDER_CAMPO).toString();
            if(input != null)
                if(input.get("orderBy") != null)
                    sql = (new StringBuilder()).append(sql).append(" DESC ").toString();
                else
                    sql = (new StringBuilder()).append(sql).append(" ASC ").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(extract(rs)));
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }

    public List readDigitaisUsuario(Long id)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManagerTwo.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select * from ").append(TABLE).append(" as ").append(TABLE).append(" left join dedos as dedos on dedos.id_dedos = ").append(TABLE).append(".id_dedos_fk").append(" where true").toString();
            sql = (new StringBuilder()).append(sql).append(" and (id_usuarios_fk = '").append(id).append("')").toString();
            sql = (new StringBuilder()).append(sql).append(" order by ").append(ORDER_CAMPO).toString();
            sql = (new StringBuilder()).append(sql).append(" ASC ").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); lista.add(extract(rs)));
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioUsuario = (String)input.get("criterioUsuario");
        if(criterioUsuario != null && !criterioUsuario.isEmpty())
            lista.add((new StringBuilder()).append("id_usuarios_fk = '").append(criterioUsuario).append("'").toString());
        return lista;
    }

    public Boolean deleteDigitaisUsuario(Connection conn, Long idUsuario)
    {
        try
        {
            String sql = (new StringBuilder()).append("delete from ").append(TABLE).append(" where id_usuarios_fk = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUsuario.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public Boolean readDigitalByIdDedo(Long idDedo, Long idUsuario)
    {
        Boolean resp = Boolean.valueOf(false);
        String sql = "";
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            sql = "select * from digitais where id_usuarios_fk = ? and id_dedos_fk = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUsuario.longValue());
            pstm.setLong(2, idDedo.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                resp = Boolean.valueOf(true);
            rs.close();
            pstm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
}
