/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connexion.Connexion;
import entities.Client;
import entities.LoginConn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yoaim
 */
public class LoginConnService {
    
    public LoginConnService() {
        
    }
    
     public boolean create(LoginConn object) {
        String query = "insert into loginconn values (null,?,?);";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.setString(2, object.getPassword());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ResultSet seConnecter(String login,String password){
         String query ="select * from loginconn where login=? and password=?";
          PreparedStatement preparedStatement;
        try {
            preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(LoginConnService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<LoginConn> findAll(){
        List<LoginConn> list = new ArrayList<>();
        try {
            String query ="select * from loginconn";
            
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(new LoginConn(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginConnService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean delete(LoginConn object) {
        String query = "delete from loginconn where id=?";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, object.getId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
     public boolean update(LoginConn object) {
        String query = "UPDATE loginconn SET login=?,password=? where id=?;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setInt(3, object.getId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
     
     public LoginConn findById(int id) {
        String query = "select * from loginconn where id=?;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new LoginConn(resultSet.getInt("id"), resultSet.getString("login"),resultSet.getString("password"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
}
