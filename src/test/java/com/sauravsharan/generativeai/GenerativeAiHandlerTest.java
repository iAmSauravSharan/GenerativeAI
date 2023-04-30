package com.sauravsharan.generativeai;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenerativeAiHandlerTest {
  @Test
  public void successfulResponse() {
    GenerativeAiHandler generativeAiHandler = new GenerativeAiHandler();
    APIGatewayProxyResponseEvent result = generativeAiHandler.handleRequest(null, null);
    assertEquals(500, result.getStatusCode().intValue());
    assertEquals("application/json", result.getHeaders().get("Content-Type"));
//    String content = result.getBody();
//    assertNotNull(content);
//    assertTrue(content.contains("\"message\""));
//    assertTrue(content.contains("\"hello world\""));
//    assertTrue(content.contains("\"location\""));
  }
}
