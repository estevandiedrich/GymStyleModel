// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DispositivoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.ModoOperacao;
import br.com.rwtech.gymstylecore.model.pojo.tipos.ModoAcesso;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            ModoOperacaoDAO

public class DispositivoDAO extends BaseDAO
{

    public DispositivoDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Dispositivo pojo = (Dispositivo)obj;
        addParametro("nome", pojo.getDispositivo(), 12);
        addParametro("endereco_ip", pojo.getEnderecoIp(), 12);
        addParametro("imprime", pojo.getImprime(), 16);
        addParametro("porta", pojo.getPorta(), 4);
        if(pojo.getModoAcesso() != null)
            addParametro("modo_acesso", Integer.valueOf(pojo.getModoAcesso().getValor()), 4);
        addParametro("entrada_dir_esq", pojo.getEntradaDirEsq(), 4);
        if(pojo.getModoOperacao() != null)
            addParametro("id_modos_operacao_fk", pojo.getModoOperacao().getId(), -1);
    }

    public void zerarUltimoEvento(Long id)
    {
        try
        {
            String sql = "UPDATE dispositivos SET ultimo_evento=0 WHERE id_dispositivos = ?";
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id.longValue());
            pstm.execute();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public Dispositivo extract(ResultSet rs)
        throws Exception
    {
        Dispositivo pojo = null;
        if(rs != null)
        {
            pojo = new Dispositivo();
            pojo.setId(rsGetId(rs));
            pojo.setDispositivo(rs.getString("nome"));
            pojo.setEnderecoIp(rs.getString("endereco_ip"));
            pojo.setMac(rs.getString("endereco_mac"));
            pojo.setOnline(Boolean.valueOf(rs.getBoolean("online")));
            pojo.setImprime(Boolean.valueOf(rs.getBoolean("imprime")));
            pojo.setEntradaDirEsq(Integer.valueOf(rs.getInt("entrada_dir_esq")));
            pojo.setPorta(Integer.valueOf(rs.getInt("porta")));
            pojo.setModoAcesso(ModoAcesso.getTipo(rs.getInt("modo_acesso")));
            pojo.setModoOperacao((ModoOperacao)DaoLocator.getModoOperacaoDAO().readById(Long.valueOf(rs.getLong("id_modos_operacao_fk"))));
        }
        return pojo;
    }
    @Override
    public String getNameTable()
    {
        return "dispositivos";
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        lista.add("ativo = true");
        String criterioNome = (String)input.get("criterioNome");
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        String criterioMac = (String)input.get("criterioMac");
        if(criterioMac != null && !criterioMac.isEmpty())
            lista.add((new StringBuilder()).append("mac = '").append(criterioMac).append("'").toString());
        String criterioOnline = (String)input.get("criterioOnline");
        if(criterioOnline != null && !criterioOnline.isEmpty())
            lista.add(" online = true");
        return lista;
    }

    public Dispositivo readByMac(String mac)
    {
        Dispositivo pojo = null;
        String sql = "";
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            sql = (new StringBuilder()).append("select * from ").append(TABLE).append(" where endereco_mac = ?").toString();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mac);
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
}
