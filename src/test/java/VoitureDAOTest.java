import esiea.dao.ReponseVoiture;
import esiea.dao.VoitureDAO;
import static org.junit.jupiter.api.Assertions.*;
import esiea.metier.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;


public class VoitureDAOTest {

    VoitureDAO vdao;
    Voiture voitureValid = new Voiture();
    Voiture newV = new Voiture();


    //************URL et connexions**************************************************************
    @Test
    public void testGetUrl() {
        String urltest = this.vdao.getUrlBase();
        assertEquals("jdbc:mysql://localhost:3306/stockcar",urltest);
    }
    //---------------------------------------------------------------------------------------------

    //************************************** SETUP ************************************************
    @Test
    public void testSetVoiture() {
        newV.setId(5555);
        newV.setMarque("TestToyota");
        newV.setModele("TestCorolla");
        newV.setFinition("TestStandard");
        newV.setCarburant(Voiture.Carburant.ESSENCE);
        newV.setKm(50000);
        newV.setAnnee(2020);
        newV.setPrix(15000);
        assertEquals(5555, newV.getId());
        assertEquals("TestToyota", newV.getMarque());
        //... meme chose pour les autres
    }

    @BeforeEach
    public void setup () {
        vdao = new VoitureDAO();
        voitureValid.setId(3333);
        voitureValid.setMarque("xxxx");
        voitureValid.setModele("xxxx");
        voitureValid.setFinition("xxxx");
        voitureValid.setCarburant(Voiture.Carburant.ESSENCE);
        voitureValid.setKm(0);
        voitureValid.setAnnee(2020);
        voitureValid.setPrix(0);
    }
    //-------------------------------------------------------------------------------------------------

    //*************************** CRUD ***************************************************************
    @Test
    public void testAjouterVoitureValid() throws SQLException {
        Voiture v = new Voiture();

        v.setMarque("testToyota");
        v.setModele("testCorolla");
        v.setFinition("test");
        v.setCarburant(Voiture.Carburant.ESSENCE);
        v.setKm(0);
        v.setAnnee(2020);
        v.setPrix(0);

        assertDoesNotThrow(() ->vdao.ajouterVoiture(v), "** Erreur survenue sur l'ajout de voiture : ");
        ReponseVoiture rv = vdao.rechercherVoitures("testToyota",0,10);
        assertEquals(1,rv.getVolume());
        vdao.supprimerVoiture(((Integer)rv.getData()[0].getId()).toString());
        rv = vdao.rechercherVoitures("testToyota",0,10);
        assertEquals(0, rv.getVolume());
    }

    @Test
    public void testSupprimerVoiture() throws SQLException {
        Voiture v = new Voiture();

        v.setMarque("yyyy");
        v.setModele("yyyy");
        v.setFinition("yyyy");
        v.setCarburant(Voiture.Carburant.ESSENCE);
        v.setKm(0);
        v.setAnnee(2020);
        v.setPrix(0);

        vdao.ajouterVoiture(v);
        ReponseVoiture rv = vdao.rechercherVoitures("yyyy",0, 10);

        assertEquals(1, rv.getVolume());
        Voiture newV2 = rv.getData()[0];

        vdao.supprimerVoiture(((Integer)newV2.getId()).toString());

        rv = vdao.rechercherVoitures("yyyy",0, 10);
        assertEquals(0,rv.getVolume());

    }

    @Test
    public void testRechercherVoitureDeMemeMarque() throws SQLException {

        Voiture v = new Voiture();
        Voiture v2 = new Voiture();
        // voiture 1
        v.setMarque("yyyy");
        v.setModele("yyyy");
        v.setFinition("yyyy");
        v.setCarburant(Voiture.Carburant.ESSENCE);
        v.setKm(40000);
        v.setAnnee(2020);
        v.setPrix(3000);
        // voiture 2
        v2.setMarque("yyyy");
        v2.setModele("yyyy2");
        v2.setFinition("yyyy2");
        v2.setCarburant(Voiture.Carburant.DIESEL);
        v2.setKm(65000);
        v2.setAnnee(2020);
        v2.setPrix(12000);
        // ajouter les 2 voitures créée
        vdao.ajouterVoiture(v);
        vdao.ajouterVoiture(v2);
        // recuperer la reponse de la fonction rechercherVoitures
        ReponseVoiture rv = vdao.rechercherVoitures("yyyy",0,10);
        // s'assurer qu'il y a bien les 2 voitures dans la reponse
        assertEquals(2, rv.getVolume());
        // supprimer les 2 voitures créée pour le test
        vdao.supprimerVoiture(((Integer)rv.getData()[0].getId()).toString());
        vdao.supprimerVoiture(((Integer)rv.getData()[1].getId()).toString());
        // s'assurer qu'il n y a plus de voiture
        rv = vdao.rechercherVoitures("yyyy",0,10);
        assertEquals(0, rv.getVolume());
    }


}
