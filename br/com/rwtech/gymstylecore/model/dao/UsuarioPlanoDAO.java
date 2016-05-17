// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UsuarioPlanoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.pojo.*;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            PagamentoDAO, PlanoDAO, DuracaoPlanoDAO, UsuarioReadDAO, 
//            ModalidadeDAO, UsuarioDAO

public class UsuarioPlanoDAO
{

    public UsuarioPlanoDAO()
    {
    }

    public void create(Connection conn, Plano plano, Long usuarioFk)
    {
        try
        {
            String sql = "INSERT INTO planos_usuarios(id_planos_fk, id_usuarios_fk, id_duracoes_plano_fk) VALUES (?, ?, ?) returning id_planos_usuarios;";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, plano.getId().longValue());
            pstm.setLong(++i, usuarioFk.longValue());
            pstm.setLong(++i, plano.getDuracaoPlano().getId().longValue());
            ResultSet rs = pstm.executeQuery();
            Long idPlanoUsuarioFk = null;
            if(rs.next())
            {
                idPlanoUsuarioFk = Long.valueOf(rs.getLong("id_planos_usuarios"));
                plano.setIdPlanoUsuario(idPlanoUsuarioFk);
            }
            if(idPlanoUsuarioFk != null)
            {
                Pagamento pagamento;
                for(Iterator i$ = plano.getPagamentos().iterator(); i$.hasNext(); DaoLocator.getPagamentoDAO().create(conn, pagamento, idPlanoUsuarioFk))
                    pagamento = (Pagamento)i$.next();

            }
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void update(Connection conn, Plano plano, Long usuarioFk)
    {
        try
        {
            String sql = "INSERT INTO planos_usuarios(id_planos_fk, id_usuarios_fk, id_duracoes_plano_fk) VALUES (?, ?, ?) returning id_planos_usuarios;";
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, plano.getId().longValue());
            pstm.setLong(++i, usuarioFk.longValue());
            pstm.setLong(++i, plano.getDuracaoPlano().getId().longValue());
            ResultSet rs = pstm.executeQuery();
            Long idPlanoUsuarioFk = null;
            if(rs.next())
                idPlanoUsuarioFk = Long.valueOf(rs.getLong("id_planos_usuarios"));
            if(idPlanoUsuarioFk != null)
            {
                Pagamento pagamento;
                for(Iterator i$ = plano.getPagamentos().iterator(); i$.hasNext(); DaoLocator.getPagamentoDAO().create(conn, pagamento, idPlanoUsuarioFk))
                    pagamento = (Pagamento)i$.next();

            }
            pstm = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public List readPlanos(Long idUsuario, Boolean completo)
    {
        List lista = new ArrayList();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from planos_usuarios as pu left join planos as p on pu.id_planos_fk = p.id_planos where id_usuarios_fk = ").append(idUsuario).append("order by id_planos_usuarios desc").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
            {
                Plano plano;
                for(; rs.next(); lista.add(plano))
                {
                    plano = null;
                    if(completo.booleanValue())
                    {
                        plano = DaoLocator.getPlanoDAO().extract(rs);
                        plano.setPagamentos(DaoLocator.getPagamentoDAO().readPagamentosByIdPlanoUsuario(Long.valueOf(rs.getLong("id_planos_usuarios"))));
                    } else
                    {
                        plano = DaoLocator.getPlanoDAO().extractSimple(rs);
                    }
                    plano.setIdPlanoUsuario(Long.valueOf(rs.getLong("id_planos_usuarios")));
                    plano.setDuracaoPlano((DuracaoPlano)DaoLocator.getDuracaoPlanoDAO().readById(Long.valueOf(rs.getLong("id_duracoes_plano_fk"))));
                    plano.setFinalizado(Boolean.valueOf(rs.getBoolean("finalizado")));
                    plano.setCancelado(Boolean.valueOf(rs.getBoolean("cancelado")));
                    plano.setValorCancelado(Double.valueOf(rs.getDouble("valor_cancelado")));
                }

            }
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

    public Plano readUltimoPlanoUsuario(Long idUsuario)
    {
        Plano plano = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from planos_usuarios as pu left join planos as p on pu.id_planos_fk = p.id_planos where true  and id_planos_usuarios =  (select MAX(id_planos_usuarios) from planos_usuarios where id_usuarios_fk = ").append(idUsuario).append(")").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null && rs.next())
            {
                plano = DaoLocator.getPlanoDAO().extract(rs);
                plano.setIdPlanoUsuario(Long.valueOf(rs.getLong("id_planos_usuarios")));
                plano.setDuracaoPlano((DuracaoPlano)DaoLocator.getDuracaoPlanoDAO().readById(Long.valueOf(rs.getLong("id_duracoes_plano_fk"))));
                plano.setPagamentos(DaoLocator.getPagamentoDAO().readPagamentosByIdPlanoUsuario(Long.valueOf(rs.getLong("id_planos_usuarios"))));
                plano.setFinalizado(Boolean.valueOf(rs.getBoolean("finalizado")));
                plano.setCancelado(Boolean.valueOf(rs.getBoolean("cancelado")));
                plano.setValorCancelado(Double.valueOf(rs.getDouble("valor_cancelado")));
            }
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return plano;
    }

    public Map readPlanoUsuarioByIdPlanoUsuario(Long idPlanoUsuario)
    {
        Map mapa = new HashMap();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select * from planos_usuarios as pu  left join planos as p on p.id_planos = pu.id_planos_fk  where id_planos_usuarios = ").append(idPlanoUsuario).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Plano plano = null;
            Usuario usuario = null;
            boolean entrou = true;
            if(rs != null && rs.next())
            {
                plano = new Plano();
                plano.setId(Long.valueOf(rs.getLong("id_planos")));
                plano.setPlano(rs.getString("nome"));
                plano.setObservacao(rs.getString("observacao"));
                plano.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
                plano.setDescontoPercentual(Integer.valueOf(rs.getInt("desconto_percentual")));
                plano.setDescontoReal(Double.valueOf(rs.getDouble("desconto_real")));
                plano.setValorMatricula(Double.valueOf(rs.getDouble("valor_matricula")));
                plano.setValor(Double.valueOf(rs.getDouble("valor")));
                plano.setValorTotal(Double.valueOf(rs.getDouble("valor_total")));
                plano.setModalidades(DaoLocator.getPlanoDAO().readModalidades(plano.getId()));
                plano.setIdPlanoUsuario(Long.valueOf(rs.getLong("id_planos_usuarios")));
                plano.setDuracaoPlano((DuracaoPlano)DaoLocator.getDuracaoPlanoDAO().readById(Long.valueOf(rs.getLong("id_duracoes_plano_fk"))));
                plano.setPagamentos(DaoLocator.getPagamentoDAO().readPagamentosByIdPlanoUsuario(Long.valueOf(rs.getLong("id_planos_usuarios"))));
                plano.setFinalizado(Boolean.valueOf(rs.getBoolean("finalizado")));
                plano.setCancelado(Boolean.valueOf(rs.getBoolean("cancelado")));
                plano.setValorCancelado(Double.valueOf(rs.getDouble("valor_cancelado")));
                if(entrou)
                {
                    usuario = DaoLocator.getUsuarioReadDAO().readByIdSimple(Long.valueOf(rs.getLong("id_usuarios_fk")));
                    entrou = false;
                }
            }
            rs.close();
            stm = null;
            mapa.put("usuario", usuario);
            mapa.put("plano", plano);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return mapa;
    }

    public void finalizarPlano(Long id)
    {
        try
        {
            String sql = "UPDATE planos_usuarios SET finalizado=true WHERE id_planos_usuarios=?";
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            pstm.setLong(++i, id.longValue());
            pstm.execute();
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cancelarPlano(Long id, Double valor)
    {
        try
        {
            String sql = "UPDATE planos_usuarios SET cancelado=true, valor_cancelado = ? WHERE id_planos_usuarios=?";
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(sql);
            int i = 0;
            try
            {
                pstm.setDouble(++i, valor.doubleValue());
            }
            catch(Exception e)
            {
                pstm.setNull(i, 8);
            }
            pstm.setLong(++i, id.longValue());
            pstm.execute();
            pstm.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Set readDispositivosPlanoUsuarioById(Long idPlanoUsuario)
    {
        Set dispositivos = new HashSet();
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select m.* from planos as p  left join planos_usuarios as pu on pu.id_planos_fk = p.id_planos  left join plano_modalidade as pm on pm.id_planos_fk = p.id_planos  left join modalidades as m on m.id_modalidades = pm.id_modalidades_fk  where true  and id_planos_usuarios = ").append(idPlanoUsuario).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                for(; rs.next(); dispositivos.addAll(DaoLocator.getModalidadeDAO().readDispositivosModalidadeById(Long.valueOf(rs.getLong("id_modalidades")))));
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dispositivos;
    }

    public Usuario readUsuarioByIdPlano(Long idPlanoUsuario)
    {
        Usuario pojo = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append(" select u.id_usuarios, u.nome,u.matricula, u.sincronizado, u.cartao_proximidade from planos_usuarios as p left join usuarios as u on u.id_usuarios = p.id_usuarios_fk where true and id_planos_usuarios = ").append(idPlanoUsuario).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null)
                while(rs.next()) 
                    pojo = DaoLocator.getUsuarioDAO().extractSimple(rs);
            rs.close();
            stm = null;
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pojo;
    }

    public Long readIdUltimoPlano(Long idUsuario)
    {
        Long id = null;
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select max(id_planos_usuarios) as id from planos_usuarios where id_usuarios_fk = ").append(idUsuario).toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs != null && rs.next())
                id = Long.valueOf(rs.getLong("id"));
            rs.close();
            stm = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return id;
    }
}
