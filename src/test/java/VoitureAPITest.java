import esiea.api.VoitureAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VoitureAPITest {


    @Test
    public void testGetVoituresJson() {
        VoitureAPI api = new VoitureAPI();

        // Test avec un paramètre "all"
        String resultAll = api.getVoituresJson("all");
        assertNotNull(resultAll);

        // Test avec un ID existant
        String resultWithValidId = api.getVoituresJson("1");
        assertNotNull(resultWithValidId);

        // Test avec un paramètre inconnu
        String resultWithUnknownParam = api.getVoituresJson("unknown");
        assertNotNull(resultWithUnknownParam);
    }

    @Test
    public void testAjouterVoiture() {
        VoitureAPI api = new VoitureAPI();
        String jsonInput = "{ \"id\": 100, \"marque\": \"Renault\", \"modele\": \"Clio\", \"finition\": \"Intens\", \"carburant\": \"E\", \"km\": 10000, \"annee\": 2020, \"prix\": 15000 }";

        String result = api.ajouterVoiture(jsonInput);
        assertNotNull(result);
        assertTrue(result.contains("succes"));
    }

    @Test
    public void testSupprimerVoiture() {
        VoitureAPI api = new VoitureAPI();

        // Supprimer une voiture qui existe
        String resultWithExistingId = api.supprimerVoiture("1");
        assertNotNull(resultWithExistingId);
        assertTrue(resultWithExistingId.contains("succes"));
    }

}