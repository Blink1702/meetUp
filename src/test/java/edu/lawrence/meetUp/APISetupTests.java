package edu.lawrence.meetUp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import edu.lawrence.meetUp.interfaces.dtos.UserDTO;
import io.restassured.RestAssured;

@SpringBootTest(classes=MeetUpApplication.class,webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class APISetupTests {
	private static UserDTO testHost;
	private static UserDTO testParticipantOne;
	private static UserDTO testParticipantTwo;
	
	@BeforeAll
	public static void setup() {
		RestAssured.port = 8085;
		RestAssured.baseURI = "http://localhost";
			
		testHost = new UserDTO();
		testHost.setName("TestHost");
		testHost.setPassword("hello");
			
		testParticipantOne = new UserDTO();
		testParticipantOne.setName("ParticipantOne");
		testParticipantOne.setPassword("hello");
		testParticipantTwo = new UserDTO();
		testParticipantTwo.setName("ParticipantTwo");
		testParticipantTwo.setPassword("hello");
	}
	
	@Test
	public void postSeller() {
		given()
		.contentType("application/json")
		.body(testHost)
		.when().post("/users").then()
		.statusCode(anyOf(is(201),is(409)));
	}
	
	@Test
	public void postBuyers() {
		given()
		.contentType("application/json")
		.body(testParticipantOne)
		.when().post("/users").then()
		.statusCode(anyOf(is(201),is(409)));
		
		given()
		.contentType("application/json")
		.body(testParticipantTwo)
		.when().post("/users").then()
		.statusCode(anyOf(is(201),is(409)));
	}

}
