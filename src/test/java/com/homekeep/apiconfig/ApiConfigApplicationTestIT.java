package com.homekeep.apiconfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiConfigApplicationTestIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void checkHealth() {
        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity("http://localhost:"+port+"/actuator/health", String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
