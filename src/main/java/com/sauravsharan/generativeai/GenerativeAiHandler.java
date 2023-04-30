package com.sauravsharan.generativeai;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sauravsharan.generativeai.model.MessageRequest;
import com.sauravsharan.generativeai.service.GenerativeAiService;
import com.sauravsharan.generativeai.util.ErrorMsgFor;
import com.sauravsharan.generativeai.util.InvalidRequestException;

import java.util.HashMap;
import java.util.Map;

import static com.sauravsharan.generativeai.util.Constants.DEFAULT_TOKEN_SIZE;
import static com.sauravsharan.generativeai.util.Preference.*;

/**
 * Handler for requests to Lambda function.
 */
public class GenerativeAiHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            Map<String, String> queryParam = input.getQueryStringParameters();
            final String vendor = queryParam.get(VENDOR);
            final String model = queryParam.get(MODEL);
            final String intention = queryParam.get(INTENTION);
            final String responseCreativity = queryParam.get(RESPONSE_CREATIVITY);
            final JsonObject requestBody = JsonParser.parseString(input.getBody()).getAsJsonObject();

            MessageRequest request = new MessageRequest("", model, vendor, intention,
                    null, Double.parseDouble(responseCreativity), DEFAULT_TOKEN_SIZE, requestBody);

            String output = GenerativeAiService.execute(request);

            if (output.isBlank()) throw new InvalidRequestException(ErrorMsgFor.UNKNOWN_ERROR);

            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (InvalidRequestException e) {
            return response
                    .withBody(e.getMessage())
                    .withStatusCode(400);
        } catch (Exception e) {
            return response
                    .withBody(ErrorMsgFor.CRASH)
                    .withStatusCode(500);
        }

    }
}
