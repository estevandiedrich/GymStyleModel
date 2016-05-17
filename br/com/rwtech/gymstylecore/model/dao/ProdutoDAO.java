// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProdutoDAO.java

package br.com.rwtech.gymstylecore.model.dao;

import br.com.rwtech.gymstylecore.model.*;
import br.com.rwtech.gymstylecore.model.pojo.Categoria;
import br.com.rwtech.gymstylecore.model.pojo.Produto;
import br.com.rwtech.gymstylecore.model.util.*;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import org.postgresql.util.Base64;

// Referenced classes of package br.com.rwtech.gymstylecore.model.dao:
//            CategoriaDAO

public class ProdutoDAO extends BaseDAO
{

    public ProdutoDAO()
    {
    }
    @Override
    public String getOrderCampo()
    {
        return "nome";
    }
    @Override
    public String getNameTable()
    {
        return "produtos";
    }
    @Override
    public void setMapPstm(Object obj)
    {
    	Produto pojo = (Produto)obj;
        addParametro("nome", pojo.getNome(), 12);
        addParametro("codigo", pojo.getCodigo(), 4);
        addParametro("estoque_atual", Integer.valueOf(pojo.getEstoqueAtual()), 4);
        addParametro("estoque_minimo", Integer.valueOf(pojo.getEstoqueMinimo()), 4);
        addParametro("preco_custo", pojo.getPrecoCusto(), 8);
        addParametro("preco_venda", pojo.getPrecoVenda(), 8);
        addParametro("observacao", pojo.getObservacao(), 12);
        addParametro("ativo", pojo.getAtivo(), 16);
        if(pojo.getCategoria() != null)
            addParametro("id_categoria_fk", pojo.getCategoria().getId(), -1);
        addParametro("codigo_barras", pojo.getCodigoBarras(), 12);
        addParametro("alerta_estoque_minimo", pojo.getAlertaEstoqueMinimo(), 16);
        if(pojo.getFoto() != null)
        {
            String foto = Base64.encodeBytes(pojo.getFoto());
            byte foto2[] = pojo.getFoto();
            int j = 0;
            int lar = 200;
            int alt = 240;
            do
            {
                if(foto.length() <= 10000)
                    break;
                foto2 = ImageManipulation.diminuiResolucaoImagem(lar, alt, pojo.getFoto());
                foto = Base64.encodeBytes(foto2);
                if(++j > 5)
                    break;
                lar -= 20;
                alt -= 20;
            } while(true);
            if(foto.length() < 10000)
                addParametro("foto", foto, 12);
            else
                System.out.println("FOTO NAO pode ser carregada");
        }
    }
    @Override
    public Produto extract(ResultSet rs)
        throws Exception
    {
        Produto pojo = null;
        if(rs != null)
        {
            pojo = new Produto();
            pojo.setId(rsGetId(rs));
            pojo.setNome(rs.getString("nome"));
            pojo.setObservacao(rs.getString("observacao"));
            pojo.setAtivo(Boolean.valueOf(rs.getBoolean("ativo")));
            pojo.setCodigo(Integer.valueOf(rs.getInt("codigo")));
            pojo.setEstoqueAtual(rs.getInt("estoque_atual"));
            pojo.setEstoqueMinimo(rs.getInt("estoque_minimo"));
            pojo.setAlertaEstoqueMinimo(Boolean.valueOf(rs.getBoolean("alerta_estoque_minimo")));
            pojo.setCodigoBarras(rs.getString("codigo_barras"));
            pojo.setPrecoCusto(Double.valueOf(rs.getDouble("preco_custo")));
            pojo.setPrecoVenda(Double.valueOf(rs.getDouble("preco_venda")));
            pojo.setCategoria((Categoria)DaoLocator.getCategoriaDAO().readById(Long.valueOf(rs.getLong("id_categoria_fk"))));
            String foto = rs.getString("foto");
            if(foto != null && !foto.isEmpty())
                pojo.setFoto(Base64.decode(foto));
        }
        return pojo;
    }
    @Override
    protected List getFiltros(Map input)
    {
        List lista = new ArrayList();
        String criterioNome = (String)input.get(Filtro.CRITERIO_NOME);
        if(criterioNome != null && !criterioNome.isEmpty())
            lista.add((new StringBuilder()).append(getRemoveAcento("nome")).append(" ilike '%").append(ConsultaUtil.normalize(criterioNome)).append("%'").toString());
        String criterioDiferenteId = (String)input.get(Filtro.CRITERIO_DIFERENTE_ID);
        if(criterioDiferenteId != null && !criterioDiferenteId.isEmpty())
            lista.add((new StringBuilder()).append(" id_produtos <>").append(criterioDiferenteId).toString());
        String criterioCodigo = (String)input.get(Filtro.CRITERIO_CODIGO);
        if(criterioCodigo != null && !criterioCodigo.isEmpty())
            lista.add((new StringBuilder()).append(" codigo = ").append(criterioCodigo).toString());
        String criterioCategoria = (String)input.get(Filtro.CRITERIO_CATEGORIA);
        if(criterioCategoria != null && !criterioCategoria.isEmpty())
            lista.add((new StringBuilder()).append(" id_categoria_fk = ").append(criterioCategoria).toString());
        String criterioAtivo = (String)input.get(Filtro.CRITERIO_ATIVO);
        if(criterioAtivo != null && !criterioAtivo.isEmpty())
            lista.add((new StringBuilder()).append(" ativo = ").append(criterioAtivo).toString());
        return lista;
    }

    public Long readNextCodigo()
    {
        Long codigo = new Long(1L);
        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = (new StringBuilder()).append("select (max(codigo)+1) as codigo from ").append(TABLE).append(" where true and ativo = true").toString();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
                codigo = Long.valueOf(rs.getLong("codigo"));
            if(codigo.longValue() == 0L)
            {
                Long long1 = codigo;
                Long long2 = codigo = Long.valueOf(codigo.longValue() + 1L);
                Long _tmp = long1;
            }
            rs.close();
            stm.close();
            conn = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return codigo;
    }
}
