package br.ufg.inf.es.integracao.dswww;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaCRUD {
   
    public void criar(Connection con, Conta conta) throws SQLException {
        String sql = "insert into conta values (?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)){
        stm.setInt(1, conta.numero);
        stm.setString(2, conta.cliente);
        stm.setDouble(3, conta.saldo);
        stm.executeUpdate();
        }
        }
    
    public List<Conta> ler(Connection con) throws SQLException {
        List<Conta> lista = new ArrayList<>();
        String sql = "select numero,cliente,saldo from conta";
        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()){
            while(rs.next()) {
                lista.add(new Conta(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        }
        
        return lista;
    }
    
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby://localhost:1527/";
        try (Connection con = DriverManager.getConnection(url, "app", "")) {
        ContaCRUD crud = new ContaCRUD();
        
        Conta conta1 = new Conta(1, "Ricardo", 1000.10);
        Conta conta2 = new Conta(2, "Everton", 10530.10);
        Conta conta3 = new Conta(2, "Manfred", 150.10);
        
        crud.criar(con, conta1);
        crud.criar(con, conta2);
        crud.criar(con, conta3);
        
        List<Conta> contas = crud.ler(con);
        for (Conta conta : contas) {
            System.out.println(conta);
        
        }
        
    }
    }
        
}
