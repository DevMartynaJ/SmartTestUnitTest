package pl.smarttesting;

import java.io.File;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.BDDAssertions.then;


/*
 * Klasa testowa uruchamiająca z Docker Compose infrastrukturę potrzebną naszej aplikacji
 * razem z naszą aplikacją. Moglibyśmy zaślepić część elementów naszej aplikacji (np.
 * wywołania do usług zewnętrznych).
 *
 * Jeśli uruchomiony w suicie testów, możemy włączyć test tylko gdy system property "dev"
 * został ustawiony na "true"
 *
 * @EnabledIfSystemProperty(named = "dev", matches = "true")
 */
@Testcontainers
class DemoApplicationTests {

	@Container
	DockerComposeContainer container = new DockerComposeContainer(new File("docker-compose.yml"))
			.withExposedService("postgres_1", 5432,  Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(10)))
			.withExposedService("rabbitmq_1", 5672,  Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(10)))
			.withExposedService("app_1", 7654,  Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(10)));

	@Test
	public void should_verify_that_the_application_is_running_in_healthy_state() {
		System.out.println("The apps are running");

		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:7654/actuator/health", String.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
