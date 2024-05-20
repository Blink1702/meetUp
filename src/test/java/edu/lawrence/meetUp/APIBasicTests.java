package edu.lawrence.meetUp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
import edu.lawrence.meetUp.interfaces.dtos.ProfileDTO;
import edu.lawrence.meetUp.interfaces.dtos.UserDTO;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest(classes=MeetUpApplication.class,webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APIBasicTests {
	private static UserDTO testHost;
	private static UserDTO testParticipantOne;
	private static UserDTO testParticipantTwo;
	private static String hostToken;
	private static String participantOneToken;
	private static String participantTwoToken;
	private static String eventID;
	
	@BeforeAll
	public static void setup() {
		RestAssured.port = 8085;
		RestAssured.baseURI = "http://localhost";
		
		testHost = new UserDTO();
		testHost.setName("TestSeller");
		testHost.setPassword("hello");
		
		testParticipantOne = new UserDTO();
		testParticipantOne.setName("BuyerOne");
		testParticipantOne.setPassword("hello");
		testParticipantTwo = new UserDTO();
		testParticipantTwo.setName("BuyerTwo");
		testParticipantTwo.setPassword("hello");
	}
	
	@Test
	@Order(1)
	public void testLogin() {
		hostToken = given()
				.contentType("application/json")
				.body(testHost)
				.when().post("/users/login")
				.then()
				.statusCode(200)
				.extract().asString();
		
		participantOneToken = given()
				.contentType("application/json")
				.body(testParticipantOne)
				.when().post("/users/login")
				.then()
				.statusCode(200)
				.extract().asString();

		participantTwoToken = given()
				.contentType("application/json")
				.body(testParticipantTwo)
				.when().post("/users/login")
				.then()
				.statusCode(200)
				.extract().asString();
	}
	
	@Test
	@Order(2)
	public void testPostProfile() {
		ProfileDTO testHostProfile = new ProfileDTO();
		testHostProfile.setName("Test Seller");
		testHostProfile.setEmail("seller@sales.com");
		testHostProfile.setPhone("9205551212");
		testHostProfile.setLocation("Appleton, WI");
		testHostProfile.setRanking("basketball:10, football:23");
		testHostProfile.setSport("basketball,football");
		
		
		given()
		.header("Authorization","Bearer "+hostToken)
		.contentType("application/json")
		.body(testHostProfile)
		.when().post("/users/profile")
		.then()
		.statusCode(anyOf(is(201),is(409)));
	}
	
	@Test
	@Order(3)
	public void testPostEvent() {
		EventDTO event = new EventDTO();
		event.setHost("Zakirul");
		event.setParticipant("Berny");
		event.setTime("May 21st");
		event.setPlace("Wellness Center");
		event.setSport("basketball");
		
		given()
		.header("Authorization","Bearer "+hostToken)
		.contentType("application/json")
		.body(event)
		.when().post("/events")
		.then()
		.statusCode(anyOf(is(201),is(409)));
	}
	
	@Test
	@Order(4)
	public void testGetEvents() {
		eventID =  
				when()
				.get("/events")
				.then()
				.statusCode(200)
				.extract()
				.path("[0].eventid");
		
		System.out.println(eventID);
	}
	
	
	/*
	@Test
	@Order(5)
	public void testPostParticipant() {
		
	}
	*/
	

}
