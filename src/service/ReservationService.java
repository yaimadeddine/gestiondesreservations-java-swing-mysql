package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Reservation;
import connexion.Connexion;
import dao.IDAO;

public class ReservationService implements IDAO<Reservation> {
    public ReservationService() {
    }

    @Override
    public boolean create(Reservation object) {
        String query = "INSERT INTO `reservation` (`id`, `client_id`, `chambre_id`, `date_debut`, `date_fin`) VALUES (NULL,?,?,?,?);";
        List<Reservation> reservations = this.findAll();
            if (reservations.isEmpty()){
                try {
                    PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
                    preparedStatement.setInt(1, object.getClient().getId());
                    preparedStatement.setInt(2, object.getChambre().getId());
                    preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
                    preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
                    if (preparedStatement.executeUpdate() == 1) {
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                for (Reservation reservation :
                        reservations) {
                    if (object.getChambre().getId() == reservation.getChambre().getId()){
                        if (object.getDatedebut().after(reservation.getDatefin())){
                            try {
                                PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
                                preparedStatement.setInt(2, object.getChambre().getId());
                                preparedStatement.setInt(1, object.getClient().getId());
                                preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
                                preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
                                if (preparedStatement.executeUpdate() == 1) {
                                    return true;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                    }else {
                        try {
                            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
                            preparedStatement.setInt(2, object.getChambre().getId());
                            preparedStatement.setInt(1, object.getClient().getId());
                            preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
                            preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
                            if (preparedStatement.executeUpdate() == 1) {
                                return true;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        return false;
    }

    @Override
    public boolean update(Reservation object) {
        String query = "UPDATE reservation SET chambre_id=?,client_id=?,date_debut=?,date_fin=? where id=?;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, object.getChambre().getId());
            preparedStatement.setInt(2, object.getClient().getId());
            preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
            preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
            preparedStatement.setInt(5, object.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Reservation object) {
        String query = "delete from reservation where id=?";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, object.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reservation findById(int id) {
        ChambreService chambreService = new ChambreService();
        ClientService clientService = new ClientService();
        String query = "select * from reservation where id=?;";
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("chambre_id")), clientService.findById(resultSet.getInt("client_id")), resultSet.getDate("date_debut"), resultSet.getDate("date_fin"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        ChambreService chambreService = new ChambreService();
        ClientService clientService = new ClientService();
        String query = "select * from reservation;";
        List<Reservation> reservations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = Connexion.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("chambre_id")), clientService.findById(resultSet.getInt("client_id")), resultSet.getDate("date_debut"), resultSet.getDate("date_fin")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reservations;
    }
    
    

}
