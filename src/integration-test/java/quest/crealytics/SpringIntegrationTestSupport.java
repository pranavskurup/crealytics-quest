package quest.crealytics;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrealyticsAppRunner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@Slf4j
public class SpringIntegrationTestSupport {
    @LocalServerPort
    protected int port;
    @Getter
    @Setter
    private WebTestClient.RequestHeadersSpec<?> spec = null;
    @Getter
    @Setter
    private WebTestClient.ResponseSpec res = null;
    @Autowired
    @Getter
    private WebTestClient webClient;

}
