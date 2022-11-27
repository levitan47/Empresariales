/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Omar Camilo Castellanos Ávila
 */
package com.uts.edu.co.appclientes.datos;

import java.sql.*;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/empresariales1ercorte?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "950610";
    
    private static BasicDataSource dataSource;
    
    public static DataSource getDataSource(){
        if (dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }
        return dataSource;
    }
    
    public static Connection GetConeccion() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs){
        try{
            rs.close();
        } catch (SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try{
            stmt.close();
        } catch (SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try{
            conn.close();
        } catch (SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
}
