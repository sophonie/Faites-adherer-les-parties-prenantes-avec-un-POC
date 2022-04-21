package fr.sofina.application.hopital;

import fr.sofina.application.SofinaApplication;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootConfiguration
@Configurable
@RunWith(Runner.class)
@SpringBootTest(classes = SofinaApplication.class)
public class HopitalRESTAPITest { // Tests API REST couche controller avec Hamcrest

    @Test
    public void testLitsDisponibles() throws IOException {
        // given
        final int valeur = 7; // code hopital
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/medhead/lits/" + valeur);
        // when
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }
    
    @Test    
    public void testFindSpecialiteByCode() throws IOException {
        // given
        final Long valeur = 11L; // code specialite
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/medhead/specialite/" + valeur);
        // when
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }
}
