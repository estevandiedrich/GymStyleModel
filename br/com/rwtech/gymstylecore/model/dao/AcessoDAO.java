// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AcessoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.*;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            PerfilAcessoDAO, FaixaDAO, DiaSemanaDAO

public class AcessoDAO extends BaseDAO
{

    public AcessoDAO()
    {
    }

    public void create(Acesso pojo, Long idUsuarioFk)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            PreparedStatement pstm = null;
            int i = 0;
            String sql = "INSERT INTO funcionarios_acesso(id_usuario_fk, livre) VALUES (?, ?) returning id_funcionarios_acesso";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setLong(++i, idUsuarioFk.longValue());
            pstm.setBoolean(++i, pojo.getLivre().booleanValue());
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                pojo.setId(rsGetId(rs));
            if(pojo.getFaixas() != null && !pojo.getFaixas().isEmpty())
                createFaixas(conn, pojo.getFaixas(), pojo.getId());
            createDispositivos(conn, pojo.getDispositivos(), pojo.getId());
            pstm.close();
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
        }
    }

    public void update(Acesso pojo)
    {
        Connection conn = null;
        try
        {
            conn = ConnectionManager.getInstance().getConnectionForTransaction();
            deleteFaixasAcesso(conn, pojo.getId());
            deleteDispositivosAcesso(conn, pojo.getId());
            PreparedStatement pstm = null;
            int i = 0;
            String sql = " UPDATE funcionarios_acesso SET  livre = ? WHERE id_funcionarios_acesso = ?";
            pstm = conn.prepareStatement(sql);
            i = 0;
            pstm.setBoolean(++i, pojo.getLivre().booleanValue());
            pstm.setLong(++i, pojo.getId().longValue());
            pstm.execute();
            if(pojo.getFaixas() != null && !pojo.getFaixas().isEmpty())
                createFaixas(conn, pojo.getFaixas(), pojo.getId());
            createDispositivos(conn, pojo.getDispositivos(), pojo.getId());
            pstm.close();
            conn.commit();
            conn.close();
            conn = null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
                conn.close();
                conn = null;
            }
            catch(Exception e1) { }
        }
    }

    private void createDispositivos(Connection conn, List list, Long idAcessoFk)
    {
        for(Iterator i$ = list.iterator(); i$.hasNext();)
        {
            Dispositivo pojo = (Dispositivo)i$.next();
            try
            {
                PreparedStatement pstm = null;
                int i = 0;
                String sql = "INSERT INTO acesso_dispositivos(id_acesso_fk, id_dispositivos_fk) VALUES (?, ?)";
                pstm = conn.prepareStatement(sql);
                i = 0;
                pstm.setLong(++i, idAcessoFk.longValue());
                pstm.setLong(++i, pojo.getId().longValue());
                pstm.execute();
                pstm.close();
            }
            catch(Exception ex) { }
        }

    }

    private void createFaixas(Connection conn, List list, Long idAcessoFk)
    {
        for(Iterator i$ = list.iterator(); i$.hasNext();)
        {
            Faixa pojo = (Faixa)i$.next();
            DaoLocator.getFaixaDAO().create(conn, pojo);
            try
            {
                PreparedStatement pstm = null;
                int i = 0;
                String sql = "INSERT INTO acesso_faixas(id_acesso_fk, id_faixas_fk) VALUES (?, ?)";
                pstm = conn.prepareStatement(sql);
                i = 0;
                pstm.setLong(++i, idAcessoFk.longValue());
                pstm.setLong(++i, pojo.getId().longValue());
                pstm.execute();
                pstm.close();
            }
            catch(Exception ex) { }
        }

    }

    public void deleteFaixasAcesso(Connection conn, Long idAcesso)
    {
        try
        {
            String sql = "DELETE FROM acesso_faixas WHERE id_acesso_fk = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idAcesso.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PerfilAcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDispositivosAcesso(Connection conn, Long idAcesso)
    {
        try
        {
            String sql = "DELETE FROM acesso_dispositivos WHERE id_acesso_fk = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idAcesso.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PerfilAcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getNameTable()
    {
        return "funcionarios_acesso";
    }
    @Override
    public Acesso extract(ResultSet rs)
        throws Exception
    {
        Acesso pojo = null;
        if(rs != null)
        {
            pojo = new Acesso();
            pojo.setId(rsGetId(rs));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
            pojo.setLivre(Boolean.valueOf(rs.getBoolean("livre")));
            if(!pojo.getLivre().booleanValue())
                pojo.setFaixas(getFaixas(pojo.getId()));
            pojo.setDispositivos(getDispositivos(pojo.getId()));
        }
        return pojo;
    }

    public List<Faixa> getFaixas(Long idAcesso)
    {
        List list = null;
        String sql = (new StringBuilder()).append(" select * from acesso_faixas as af left join faixas as f on af.id_faixas_fk = f.id_faixas  where true and id_acesso_fk = ").append(idAcesso).toString();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            list = new ArrayList();
            Faixa pojo;
            for(; rs.next(); list.add(pojo))
            {
                pojo = new Faixa();
                pojo.setId(Long.valueOf(rs.getLong("id_faixas")));
                pojo.setDiaSemana((DiaSemana)DaoLocator.getDiaSemanaDAO().readById(Long.valueOf(rs.getLong("id_dias_semana_fk"))));
                pojo.setHorarioInicio(CalendarUtil.setTimeCalendar(rs.getTime("horario_inicio")));
                pojo.setHorarioFim(CalendarUtil.setTimeCalendar(rs.getTime("horario_fim")));
            }

        }
        catch(Exception ex)
        {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Dispositivo> getDispositivos(Long idAcesso)
    {
        List list = null;
        String sql = (new StringBuilder()).append(" select * from acesso_dispositivos as ad left join dispositivos as d on ad.id_dispositivos_fk = d.id_dispositivos where true and id_acesso_fk = ").append(idAcesso).toString();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            list = new ArrayList();
            Dispositivo pojo;
            for(; rs.next(); list.add(pojo))
            {
                pojo = new Dispositivo();
                pojo.setId(Long.valueOf(rs.getLong("id_dispositivos")));
                pojo.setDispositivo(rs.getString("nome"));
                pojo.setEnderecoIp(rs.getString("endereco_ip"));
                pojo.setMac(rs.getString("endereco_mac"));
                pojo.setOnline(Boolean.valueOf(rs.getBoolean("online")));
                pojo.setImprime(Boolean.valueOf(rs.getBoolean("imprime")));
                pojo.setEntradaDirEsq(Integer.valueOf(rs.getInt("entrada_dir_esq")));
                pojo.setPorta(Integer.valueOf(rs.getInt("porta")));
            }

        }
        catch(Exception ex)
        {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Boolean delete(Long idUsuario, Long id)
    {
        Connection conn = ConnectionManager.getInstance().getConnectionForTransaction();
        String sql = "";
        try
        {
            sql = " UPDATE funcionarios_acesso SET ativo=false WHERE id_funcionarios_acesso = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
            return Boolean.valueOf(true);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sql = "DELETE FROM funcionarios_acesso WHERE id_funcionarios_acesso in ( select id_funcionarios_acesso from funcionarios_acesso where id_usuario_fk = ? and id_funcionarios_acesso != ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, idUsuario.longValue());
            pstm.setLong(2, id.longValue());
            pstm.execute();
            return Boolean.valueOf(true);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Boolean.valueOf(false);
    }

    public Acesso readByIdUsuario(Long id)
    {
        Acesso pojo = null;
        String sql = "";
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            sql = (new StringBuilder()).append("  select * from funcionarios_acesso as f  left join acesso_dispositivos as a on a.id_acesso_fk = f.id_funcionarios_acesso left join dispositivos as d on d.id_dispositivos = a.id_dispositivos_fk left join usuarios as u on u.id_usuarios = f.id_usuario_fk where true and u.ativo_funcionario = true and f.id_funcionarios_acesso = (").append(SQL_MAX_ID_ACESSO).append(")").toString();
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

    public static String SQL_MAX_ID_ACESSO = "SELECT max(id_funcionarios_acesso) AS id_funcionarios_acesso FROM funcionarios_acesso WHERE id_usuario_fk = ?";

}
