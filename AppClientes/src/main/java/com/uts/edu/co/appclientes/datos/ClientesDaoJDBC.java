/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Omar Camilo Castellanos Ávila
 */
package com.uts.edu.co.appclientes.datos;

import com.uts.edu.co.appclientes.dominio.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo" 
            +" From clientes";
    
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo" 
            +" From clientes where id_cliente = ?";
    
    private static final String SQL_INSERT = "INSERT INTO Cliente(id_cliente, nombre, apellido, email, telefono, saldo) " 
            +" VALUES (?,?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE id_cliente, nombre, apellido, email, telefono, saldo" 
            +" SET nombre=?, apellido=?, email, telefono, saldo=?, WHERE id_cliente=?";
    
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";
    
    public List<Clientes> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cliente = null;
        List<Clientes> clientes = new ArrayList<>();
        try {
            conn = Conexion.GetConeccion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                
                cliente = new Clientes(idCliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);
            }
            }catch (SQLException ex){
                    ex.printStackTrace(System.out);
            }finally {
                    Conexion.close(rs);
                    Conexion.close(stmt);
                    Conexion.close(conn);
            }
            return clientes;
        }
        public Clientes encontrar (Clientes cliente){
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try{
                conn = Conexion.GetConeccion();
                stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
                rs = stmt.executeQuery();
                rs.absolute(1);
               
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEmail(email);
                cliente.setTelefono(telefono);
                cliente.setSaldo(saldo);
            
            } catch (SQLException ex){
                ex.printStackTrace(System.out);
            } finally {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return cliente;
    }
        public int insertar (Clientes cliente){
            Connection conn = null;
            PreparedStatement stmt = null;
            int rows = 0;
            try{
                conn = Conexion.GetConeccion();
                stmt = conn.prepareStatement(SQL_INSERT);
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getApellido());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getTelefono());
                stmt.setDouble(5, cliente.getSaldo());
                
                rows = stmt.executeUpdate();
            }catch (SQLException ex){
                ex.printStackTrace(System.out);
            }finally{
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
        }
        public int actualizar (Clientes cliente){
            Connection conn = null;
            PreparedStatement stmt = null;
            int rows = 0;
            try{
                conn = Conexion.GetConeccion();
                stmt = conn.prepareStatement(SQL_UPDATE);
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getApellido());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getTelefono());
                stmt.setDouble(5, cliente.getSaldo());
                stmt.setInt(6, cliente.getIdCliente());
                
                rows = stmt.executeUpdate();
            }catch (SQLException ex){
                ex.printStackTrace(System.out);
            }finally{
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
        }
        public int eliminar (Clientes cliente){
            Connection conn = null;
            PreparedStatement stmt = null;
            int rows = 0;
            try {
                conn = Conexion.GetConeccion();
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setInt(1, cliente.getIdCliente());
                
                rows = stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            } finally {
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
        }
}
