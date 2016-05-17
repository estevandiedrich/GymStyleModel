// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseDaoMySQL.java

package br.com.rwtech.gymstylecore.model;

import br.com.rwtech.gymstylecore.model.pojo.POJO;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model:
//            BaseDAO, ConnectionManager

public abstract class BaseDaoMySQL
{

    public BaseDaoMySQL()
    {
        TABLE = getNameTable();
        ORDER_CAMPO = getOrderCampo();
        campos = new ArrayList();
    }

    public void addParametro(String coluna, Object valor, int tipo)
    {
        Map parametro = new HashMap();
        parametro.put("coluna", coluna);
        parametro.put("valor", valor);
        parametro.put("tipo", Integer.valueOf(tipo));
        campos.add(parametro);
    }

    public String getOrderCampo()
    {
        return (new StringBuilder()).append("id_").append(TABLE).toString();
    }

    public abstract String getNameTable();

    private String montarSqlCreate()
    {
        String sql = "";
        String colunas = "";
        String valores = "";
        if(!campos.isEmpty())
        {
            int i = 0;
            do
            {
                if(i >= campos.size())
                    break;
                colunas = (new StringBuilder()).append(colunas).append("").append(((Map)campos.get(i)).get("coluna")).toString();
                valores = (new StringBuilder()).append(valores).append("?").toString();
                if(++i < campos.size())
                {
                    colunas = (new StringBuilder()).append(colunas).append(",").toString();
                    valores = (new StringBuilder()).append(valores).append(",").toString();
                }
            } while(true);
            sql = (new StringBuilder()).append("INSERT INTO ").append(TABLE).append(" (").append(colunas).append(") ").append(" values (").append(valores).append(") ").toString();
        }
        return sql;
    }

    public void create(Connection conn, Object pojo)
    {
        try
        {
            campos.clear();
            setMapPstm(pojo);
            String sql = montarSqlCreate();
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            i = 1;
            for(int j = 0; j < campos.size(); j++)
            {
                setPreparedStetement(pstm, ((Map)campos.get(j)).get("valor"), ((Integer)((Map)campos.get(j)).get("tipo")).intValue());
                i++;
            }

            ResultSet rs = pstm.executeQuery();
            if(rs.next())
            {
                POJO aux = (POJO)pojo;
                aux.setId(rsGetId(rs));
            }
            rs.close();
            pstm.close();
            campos.clear();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setPreparedStetement(PreparedStatement pstm, Object valor, int tipo)
    {
        if(valor == null)
            try
            {
                pstm.setNull(i, tipo);
            }
            catch(SQLException ex)
            {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        else
            try
            {
                switch(tipo)
                {
                case 12: // '\f'
                    pstm.setString(i, (String)valor);
                    break;

                case 91: // '['
                    Calendar data = (Calendar)valor;
                    pstm.setDate(i, CalendarUtil.setDateSqlCalendar(data));
                    break;

                case 92: // '\\'
                    Calendar time = (Calendar)valor;
                    pstm.setTime(i, CalendarUtil.setTimeSqlCalendar(time));
                    break;

                case 93: // ']'
                    pstm.setTimestamp(i, (Timestamp)valor);
                    break;

                case 4: // '\004'
                    pstm.setInt(i, ((Integer)valor).intValue());
                    break;

                case 2003: 
                    byte var[] = (byte[])(byte[])valor;
                    pstm.setBytes(i, var);
                    break;

                case 16: // '\020'
                    pstm.setBoolean(i, ((Boolean)valor).booleanValue());
                    break;

                case -1: 
                    pstm.setLong(i, ((Long)valor).longValue());
                    break;

                case -5: 
                    pstm.setLong(i, ((Long)valor).longValue());
                    break;

                case 8: // '\b'
                    pstm.setDouble(i, ((Double)valor).doubleValue());
                    break;
                }
            }
            catch(SQLException ex)
            {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void setMapPstm(Object obj)
    {
    }

    private String montarSqlUpdate()
    {
        String sql = "";
        String colunas = "";
        if(!campos.isEmpty())
        {
            int i = 0;
            do
            {
                if(i >= campos.size())
                    break;
                colunas = (new StringBuilder()).append(colunas).append(((Map)campos.get(i)).get("coluna")).append(" = ?").toString();
                if(++i < campos.size())
                    colunas = (new StringBuilder()).append(colunas).append(",").toString();
            } while(true);
            sql = (new StringBuilder()).append(" UPDATE ").append(TABLE).append(" set ").append(colunas).append(" where id_").append(TABLE).append(" = ?").toString();
        }
        return sql;
    }

    public void update(Connection conn, Object pojo)
    {
        try
        {
            campos.clear();
            setMapPstm(pojo);
            String sql = montarSqlUpdate();
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            i = 1;
            for(int j = 0; j < campos.size(); j++)
            {
                setPreparedStetement(pstm, ((Map)campos.get(j)).get("valor"), ((Integer)((Map)campos.get(j)).get("tipo")).intValue());
                i++;
            }

            POJO pojoAux = (POJO)pojo;
            pstm.setLong(i, pojoAux.getId().longValue());
            pstm.execute();
            pstm.close();
            campos.clear();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Boolean delete(Connection conn, Long id)
    {
        try
        {
            String sql = (new StringBuilder()).append("delete from ").append(TABLE).append(" where id_").append(TABLE).append(" = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
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

    public Boolean disabled(Connection conn, Long id)
    {
        try
        {
            String sql = (new StringBuilder()).append("update ").append(TABLE).append(" set ativo = false where id_").append(TABLE).append(" = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
            pstm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public Object readById(Long id)
    {
        Object pojo = null;
        String sql = "";
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where id_").append(TABLE).append(" = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo = extract(rs);
            rs.close();
            pstm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojo;
    }

    public abstract Object extract(ResultSet resultset)
        throws Exception;

    public Object extractSimple(ResultSet rs)
        throws Exception
    {
        return extract(rs);
    }

    protected List getFiltros(Map input)
    {
        return new ArrayList();
    }

    public List readByCriteria(Map input)
    {
        List lista = new ArrayList();
        List listaCriterios = getFiltros(input);
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where true").toString();
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

    public Long rsGetId(ResultSet rs)
        throws Exception
    {
        return Long.valueOf(rs.getLong((new StringBuilder()).append("id_").append(TABLE).toString()));
    }

    public Map paginator(Map input)
    {
        Map map = new HashMap();
        Integer p = Integer.valueOf(1);
        Integer prev = Integer.valueOf(1);
        Integer next = Integer.valueOf(2);
        Integer paginas = Integer.valueOf(0);
        if(input.get("pag.p") != null)
        {
            String pagina = (String)input.get("pag.p");
            if(!pagina.isEmpty())
                p = Integer.valueOf(Integer.parseInt(pagina));
        }
        prev = Integer.valueOf(p.intValue() - 1);
        next = Integer.valueOf(p.intValue() + 1);
        List listaCriterios = getFiltros(input);
        String filtros = "";
        for(int i = 0; i < listaCriterios.size(); i++)
            filtros = (new StringBuilder()).append(filtros).append(" and (").append((String)listaCriterios.get(i)).append(")").toString();

        List list = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("SELECT count(id_").append(TABLE).append(") as tam FROM ").append(TABLE).append(" where true").toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            float tamanho = 0.0F;
            if(rs != null && rs.next())
                tamanho = rs.getInt("tam");
            if(tamanho < 10F)
            {
                paginas = Integer.valueOf(1);
                next = Integer.valueOf(0);
                p = Integer.valueOf(1);
                prev = Integer.valueOf(0);
            } else
            {
                paginas = Integer.valueOf((int)tamanho / 10);
                if(tamanho % 10F != 0.0F)
                    paginas = Integer.valueOf(paginas.intValue() + 1);
            }
            if(p.intValue() > paginas.intValue())
            {
                p = Integer.valueOf(1);
                prev = Integer.valueOf(0);
                next = Integer.valueOf(p.intValue() + 1);
            }
            if(p == paginas)
                next = Integer.valueOf(0);
            sql = (new StringBuilder()).append("SELECT * FROM ").append(TABLE).append(" where true ").toString();
            sql = (new StringBuilder()).append(sql).append(filtros).toString();
            sql = (new StringBuilder()).append(sql).append(" order by ").append(ORDER_CAMPO).toString();
            if(input != null)
            {
                String orderBy = "false";
                map.put("order", "Crescente!");
                map.put("orderBy", Boolean.valueOf(false));
                if(input.get("orderBy") != null)
                {
                    orderBy = (String)input.get("orderBy");
                    if(orderBy.equalsIgnoreCase("true"))
                    {
                        map.put("order", "Decrescente!");
                        map.put("orderBy", Boolean.valueOf(true));
                    }
                }
                if(orderBy.equalsIgnoreCase("true"))
                    sql = (new StringBuilder()).append(sql).append(" DESC ").toString();
                else
                    sql = (new StringBuilder()).append(sql).append(" ASC ").toString();
            }
            sql = (new StringBuilder()).append(sql).append(" LIMIT 10 OFFSET (").append(p.intValue() - 1).append(" * 10)").toString();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); list.add(extract(rs)));
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        map.put("list", list);
        map.put("currentPage", p);
        map.put("p", p);
        if(next.intValue() == 0)
            map.put("next", Boolean.valueOf(false));
        else
            map.put("next", Boolean.valueOf(true));
        map.put("nextPage", next);
        if(prev.intValue() == 0)
            map.put("prev", Boolean.valueOf(false));
        else
            map.put("prev", Boolean.valueOf(true));
        map.put("prevPage", prev);
        map.put("paginas", paginas);
        map.put("qtdePaginas", getMapSizePage(paginas.intValue()));
        return map;
    }

    public static Map getMapSizePage(int qtde)
    {
        Map mapa = new LinkedHashMap();
        for(int i = 1; i <= qtde; i++)
            mapa.put(new Long(i), new Long(i));

        return mapa;
    }

    public static String getRemoveAcento(String coluna)
    {
        return (new StringBuilder()).append("removeacento(").append(coluna).append(")").toString();
    }

    protected String TABLE;
    protected String ORDER_CAMPO;
    private List campos;
    private int i;
}
