package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Categorie;
import entities.Chambre;
import entities.Client;
import connexion.Connexion;
import dao.IDAO;

public class ChambreService implements IDAO<Chambre> {
    public ChambreService() {

    }

    @Override
    public boolean create(Chambre object) {
        String query = " insert into chambre values (null,?,?);";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setString(1, object.getTelephone());
            preparedStatement.setInt(2, object.getCategorie().getId());
            if(preparedStatement.executeUpdate()==1) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Chambre object) {
        String query = "UPDATE chambre SET telephone=?,categorie_id=? where id=?;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setString(1, object.getTelephone());
            preparedStatement.setInt(2, object.getCategorie().getId());
            preparedStatement.setInt(3, object.getId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Chambre object) {
        String query = "delete from chambre where id=?";
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

    @Override
    public Chambre findById(int id) {
        CategorieService categorieService = new CategorieService();
        String query = "select * from chambre where id=?;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new Chambre(resultSet.getInt("id"),resultSet.getString("telephone"),new Categorie(resultSet.getInt("categorie_id"),categorieService.findById(resultSet.getInt("categorie_id")).getCode(),categorieService.findById(resultSet.getInt("categorie_id")).getLibelle()));
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chambre> findAll() {
        CategorieService categorieService = new CategorieService();
        String query = "select * from chambre ORDER BY categorie_id;";
        List<Chambre> chambres = new ArrayList<Chambre>();
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                chambres.add(new Chambre(resultSet.getInt("id"),resultSet.getString("telephone"),new Categorie(resultSet.getInt("categorie_id"),categorieService.findById(resultSet.getInt("categorie_id")).getCode(),categorieService.findById(resultSet.getInt("categorie_id")).getLibelle())));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return chambres;
    }

    public List<Chambre> findChambreBetweenDates (Client client, Date dateDebut,Date dateFin){
        List<Chambre> chambres = new ArrayList<>();
        ChambreService chambreService = new ChambreService();
        String query = "SELECT chambre_id FROM reservation WHERE client_id=? and (date_debut>?) and (date_fin<?) ;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
             preparedStatement.setInt(1, client.getId());
             preparedStatement.setDate(2, new java.sql.Date(dateDebut.getTime()));
             preparedStatement.setDate(3, new java.sql.Date(dateFin.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                chambres.add(new Chambre(resultSet.getInt("chambre_id"),chambreService.findById(resultSet.getInt("chambre_id")).getTelephone(),chambreService.findById(resultSet.getInt("chambre_id")).getCategorie()));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return chambres;
    }

    public List<Chambre> findChambreByCategorie(Categorie categorie){
        List<Chambre> listChambre;

        return null;
    }

}
