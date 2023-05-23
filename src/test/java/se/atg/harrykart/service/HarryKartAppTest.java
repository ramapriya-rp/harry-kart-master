package se.atg.harrykart.service;
/*
 * package se.atg.service.harrykart.java;
 * 
 * import io.restassured.RestAssured; import io.restassured.http.ContentType;
 * import org.junit.jupiter.api.BeforeAll; import
 * org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.TestInstance; import
 * org.junit.jupiter.api.extension.ExtendWith; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.ActiveProfiles; import
 * org.springframework.test.context.junit.jupiter.SpringExtension;
 * 
 * import java.net.URI;
 * 
 * import static io.restassured.RestAssured.given; import static
 * io.restassured.RestAssured.when; import static org.hamcrest.Matchers.equalTo;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.core.io.ClassPathResource; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.context.junit.jupiter.SpringExtension; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.util.StreamUtils;
 * 
 * import java.io.IOException; import java.nio.charset.Charset;
 * 
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * @ExtendWith(SpringExtension.class)
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc public class HarryKartAppTest {
 * 
 * 
 * private final MockMvc mockMvc;
 * 
 * public HarryKartAppTest(MockMvc mockMvc) { this.mockMvc = mockMvc; }
 * 
 * @Test public void testPlayHarryKart_Successful() throws Exception { // Path
 * to the input XML file String xmlFilePath = "path/to/input.xml";
 * 
 * // Read the XML file content String xmlContent; try { ClassPathResource
 * resource = new ClassPathResource(xmlFilePath); xmlContent =
 * StreamUtils.copyToString(resource.getInputStream(),
 * Charset.defaultCharset()); } catch (IOException e) { // Handle the exception
 * if the file is not found or cannot be read // You can log an error or throw
 * an exception throw new RuntimeException("Error reading XML file", e); }
 * 
 * // Make the HTTP request with the XML content HttpHeaders headers = new
 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_XML);
 * 
 * HttpEntity<String> requestEntity = new HttpEntity<>(xmlContent, headers);
 * 
 * // Send the request and handle the response ResponseEntity<String>
 * responseEntity = mockMvc.perform(post("/play")
 * .contentType(MediaType.APPLICATION_XML) .content(xmlContent))
 * .andExpect(status().isOk()) .andReturn() .getResponse();
 * 
 * // Extract the response body String responseBody = responseEntity.getBody();
 * 
 * // Assert the response
 * assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
 * assertThat(responseBody).isNotEmpty();
 * 
 * // Process the JSON response // ... }
 * 
 * @Test public void testPlayHarryKart_InvalidInput() throws Exception { // Path
 * to the invalid input XML file String xmlFilePath =
 * "path/to/invalid-input.xml";
 * 
 * // Read the invalid XML file content String xmlContent; try {
 * ClassPathResource resource = new ClassPathResource(xmlFilePath); xmlContent =
 * StreamUtils.copyToString(resource.getInputStream(),
 * Charset.defaultCharset()); } catch (IOException e) { // Handle the exception
 * if the file is not found or cannot be read // You can log an error or throw
 * an exception throw new RuntimeException("Error reading XML file", e); }
 * 
 * // Make the HTTP request with the invalid XML content HttpHeaders headers =
 * new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_XML);
 * 
 * HttpEntity<String> requestEntity = new HttpEntity<>(xmlContent, headers);
 * 
 * // Send the request and handle the response ResponseEntity<String>
 * responseEntity = mockMvc.perform(post("/play")
 * .contentType(MediaType.APPLICATION_XML) .content(xmlContent))
 * .andExpect(status().isBadRequest()) .andReturn() .getResponse();
 * 
 * // Extract the response body String responseBody = responseEntity.getBody();
 * 
 * // Assert the response
 * assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
 * assertThat(responseBody).isNotEmpty();
 * 
 * // Proceshis the error response // ... } }
 * 
 * 
 * private final static URI harryKartApp =
 * URI.create("http://localhost:8181/java/api/play");
 * 
 * @BeforeAll void setUp() {
 * RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); }
 * 
 * @Test
 * 
 * @DisplayName("Trying to GET instead of POST should return 405 Method not allowed"
 * ) void useGetOnPostEndpointShouldNotBePossible() { when() .get(harryKartApp)
 * .then() .assertThat() .statusCode(405); }
 * 
 * @Test
 * 
 * @DisplayName("The application doesn't know how to play yet") void
 * cantPlayYet() { given() .header("Content-Type", ContentType.XML) .when()
 * .post(harryKartApp) .then() .assertThat() .statusCode(200) .and()
 * .header("Content-Type", ContentType.JSON.toString()) .and() .body("message",
 * equalTo("Don't know how to play yet")); }
 * 
 */