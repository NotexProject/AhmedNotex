/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esprit.Projet.Controllers;


import Esprit.Projet.Connexion.MaConnexion;
import Esprit.Projet.Entities.Offre;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ahmed
 */
public class OffreCRUDController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdated;
    @FXML
    private TextField tfdatef;
    @FXML
    private TextArea tfdescription;
    @FXML
    private TableView<Offre> tvoffre;
    @FXML
    private TableColumn<Offre, Integer> colid;
    @FXML
    private TableColumn<Offre, String> colnom;
    @FXML
    private TableColumn<Offre, String> coldated;
    @FXML
    private TableColumn<Offre, String> coldatef;
    @FXML
    private TableColumn<Offre, String> coldescription;
    @FXML
    private Button btninserrer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField tfimg;

    @FXML
    private TextField tfcouleur;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btninserrer ){
            ajouterOffre();
        }else if(event.getSource()==btnmodifier){
        modifierOffre();
    }else if(event.getSource()==btnsupprimer){
        effacerOffre();
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherOffre();
    }    
    

Connection cnxs;
    public OffreCRUDController() {
        cnxs = MaConnexion.getInstance().getCnx();
        
    }
    
    
    public ObservableList<Offre> getOffresList(){
        ObservableList <Offre> myList = FXCollections.observableArrayList();
        try {
            
            String requete = "SELECT * FROM offre";
                    Statement st = cnxs.createStatement();
                 ResultSet rs = st.executeQuery(requete);
                    while(rs.next()){
                        Offre o = new Offre(); 
                        o.setId(rs.getInt("id"));
                        o.setNomoffre(rs.getString("nomoffre"));
                        o.setDatedebut(rs.getString("datedebut"));
                        o.setDatefin(rs.getString("datefin"));
                        o.setDescription(rs.getString("description"));
                        o.setImgsrc(rs.getString("imgsrc"));
                        o.setCouleur(rs.getString("couleur"));
                        myList.add(o);
                    }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
        
    

public void afficherOffre(){
    ObservableList<Offre> list = getOffresList();
    
    colid.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<Offre,String>("nomoffre"));
    coldated.setCellValueFactory(new PropertyValueFactory<Offre,String>("datedebut"));
    coldatef.setCellValueFactory(new PropertyValueFactory<Offre,String>("datefin"));
    coldescription.setCellValueFactory(new PropertyValueFactory<Offre,String>("description"));
    
    tvoffre.setItems(list);

}


       
public void ajouterOffre(){
        try {
            String requete = "INSERT INTO OFFRE VALUES ("+tfid.getText()+",'"+tfnom.getText()+ "','" + tfdated.getText() + "','" +tfdatef.getText()+ "','" +tfdescription.getText()+ "','" +tfimg.getText()+ "','" +tfcouleur.getText()+ "')";
        PreparedStatement pst = cnxs.prepareStatement(requete);
          pst.executeUpdate();
            System.out.println("votre offre est ajoutée avec succees ");
                } catch (SQLException ex) {
                      System.err.println(ex.getMessage());
        }
        afficherOffre();
    }

public void modifierOffre(){
        try {
            String requete = "UPDATE OFFRE SET nomoffre = '"+tfnom.getText()+ "',datedebut ='" 
                    + tfdated.getText() + "',datefin ='" +tfdatef.getText()+  "',description ='"+tfdescription.getText()+"',imgsrc ='"+tfcouleur.getText()+ "',couleur ='" 
                    +tfdescription.getText()+ "'WHERE id =" + tfid.getText()+"";
        PreparedStatement pst = cnxs.prepareStatement(requete);
          pst.executeUpdate();
            System.out.println("votre offre est modifier avec succees ");
                } catch (SQLException ex) {
                      System.err.println(ex.getMessage());
        }
        afficherOffre();
    }
public void effacerOffre(){
        try {
            String requete = "DELETE FROM OFFRE WHERE id = "+tfid.getText()+"";
        PreparedStatement pst = cnxs.prepareStatement(requete);
          pst.executeUpdate();
            System.out.println("votre offre est modifier avec succees ");
                } catch (SQLException ex) {
                      System.err.println(ex.getMessage());
        }
        afficherOffre();
    }






}
