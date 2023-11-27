import esiea.metier.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VoitureTest {

    private Voiture voiture;


    @BeforeEach
    public void setup() {
        voiture = new Voiture();
        voiture.setId(7777);
        voiture.setMarque("Toyota");
        voiture.setModele("Corolla");
        voiture.setFinition("Standard");
        voiture.setCarburant(Voiture.Carburant.ESSENCE);
        voiture.setKm(50000);
        voiture.setAnnee(2020);
        voiture.setPrix(15000);
    }

    @Test
    public void getIdTest () {
        assertEquals(7777, voiture.getId());
    }

    @Test
    public void testCheckTrueAnnee () {
        assertTrue(voiture.check());
    }

    // une date superieur a celle d'aujourd'hui
    @Test
    public void testCheckFalseAnnee () {
        voiture.setAnnee(-2020);
        assertFalse(voiture.check());
        voiture.setAnnee(2020);
    }

    @Test
    public void testCheckFalseId () {
        voiture.setId(-7777);
        assertFalse(voiture.check());
    }

    @Test
    public void testCheckFalsePrix () {
        voiture.setPrix(-15000);
        assertFalse(voiture.check());
    }

    @Test
    public void testGetTypeDonnee() {
        assertEquals("string", Voiture.getTypeDonnee("marque"));
        assertEquals("string", Voiture.getTypeDonnee("modele"));
        assertEquals("string", Voiture.getTypeDonnee("finition"));
        assertEquals("entier", Voiture.getTypeDonnee("id"));
        assertEquals("entier", Voiture.getTypeDonnee("annee"));
        assertEquals("entier", Voiture.getTypeDonnee("km"));
        assertEquals("entier", Voiture.getTypeDonnee("prix"));
        // Cas où l'attribut n'existe pas
        assertEquals("", Voiture.getTypeDonnee("inexistant"));
    }
}
