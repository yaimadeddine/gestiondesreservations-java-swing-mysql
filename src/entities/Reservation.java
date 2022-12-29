package entities;

import java.util.Date;

public class Reservation {
    private static int count;
    private int id;
    private Chambre chambre;
    private Client client;
    private Date datedebut;
    private Date datefin;

    public Reservation(Chambre chambre, Client client, Date datedebut, Date datefin) {
        this.id = ++count;
        this.chambre = chambre;
        this.client = client;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }


    public Reservation(int id, Chambre chambre, Client client, Date datedebut, Date datefin) {
        this.id = id;
        this.chambre = chambre;
        this.client = client;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", chambre=" + chambre +
                ", client=" + client +
                ", datedebut=" + datedebut +
                ", datefin=" + datefin +
                '}';
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }


}
