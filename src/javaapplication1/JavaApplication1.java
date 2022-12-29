/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import entities.Categorie;
import entities.Chambre;
import entities.Client;
import entities.Reservation;
import connexion.Connexion;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import service.CategorieService;
import service.ChambreService;
import service.ClientService;
import service.ReservationService;

/**
 *
 * @author yoaim
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public void main() {
         Connexion.getConnection();

        try {
            String createLoginConn="create table loginconn("
                    +"id int primary key auto_increment,"
                    +"login varchar(100),"
                    +"password varchar(100));";               
            String createClient="create table client("
                    +"id int primary key auto_increment,"
                    +"nom varchar(100),"
                    +"prenom varchar(100),"
                    +"telephone varchar(100),"
                    +"email varchar(100));";
            String createChambre="create table chambre("
                    +"id int primary key auto_increment,"
                    +"telephone varchar(100),"
                    +"categorie_id int,"
                    +"constraint fk3 foreign key(categorie_id) references categorie(id) on delete cascade on update cascade );";
            String createCategorie="create table categorie("
                    +"id int primary key auto_increment,"
                    +"code varchar(100),"
                    +"libelle varchar(100));";
            String createReservation="create table reservation("
                    +"id int auto_increment,"
                    +"client_id int,"
                    +"chambre_id int,"
                    +"date_debut date,"
                    +"date_fin date,"
                    +"primary key(id),"
                    +"constraint fk_v foreign key(client_id) references client (id) on delete cascade on update cascade,"
                    +"constraint fk_g foreign key(chambre_id) references chambre(id) on delete cascade on update cascade );";


            Statement statement=Connexion.getConnection().createStatement();
            statement.execute("drop table if exists loginconn");
            statement.execute("drop table if exists reservation");
            statement.execute("drop table if exists chambre");
            statement.execute("drop table if exists categorie");
            statement.execute("drop table if exists client");

            statement.executeUpdate(createLoginConn);
            statement.executeUpdate(createClient);
            statement.executeUpdate(createCategorie);
            statement.executeUpdate(createChambre);
            statement.executeUpdate(createReservation);

        } catch (SQLException e) {
            e.printStackTrace();
        }

       /* ClientService clientService = new ClientService();
        ChambreService chambreService = new ChambreService();
        CategorieService categorieService = new CategorieService();
        ReservationService reservationService = new ReservationService();

//		System.out.println(clientService.findAll());

        Client client1 = new Client("aimadeddine1", "yousri1", "1111", "yousri1@gmail.com");
        Client client2 = new Client("aimadeddine2", "yousri2", "2222", "yousri2@gmail.com");

        clientService.create(client1);
        clientService.create(client2);
//		System.out.println(clientService.toString());
//
		Categorie categorie1 = new Categorie("code1", "simple");
		Categorie categorie2 = new Categorie("code2", "simple");

		categorieService.create(categorie1);
		categorieService.create(categorie2);
//
//		System.out.println(categorieService.findAll());
//		System.out.println(categorieService.toString());
//
//
		Chambre chambre1 = new Chambre("11111",categorieService.findById(1));
		Chambre chambre2 = new Chambre("22222",categorieService.findById(2));

		chambreService.create(chambre1);
		chambreService.create(chambre2);
//		System.out.println(chambreService.toString());
//
		Reservation reservation1 = new Reservation(chambreService.findById(1), clientService.findById(1), new Date(new Date("01/01/2022").getTime()), new Date(new Date("02/01/2022").getTime()));
		Reservation reservation2 = new Reservation(chambreService.findById(1), clientService.findById(2), new Date(new Date("03/01/2022").getTime()), new Date(new Date("05/01/2022").getTime()));
//		Reservation reservation2 = new Reservation(chambreService.findById(2), clientService.findById(1), new Date("02/01/2022"), new Date("01/03/2022"));
        reservationService.create(reservation1);
		reservationService.create(reservation2);

//        System.out.println(reservationService.findAll().toString());
//
//
//		System.out.println(reservationService.toString());
//
//		System.out.println(clientService.findById(1).toString());
//		System.out.println(reservationService.findById(6));
//*/
    }
    
}
