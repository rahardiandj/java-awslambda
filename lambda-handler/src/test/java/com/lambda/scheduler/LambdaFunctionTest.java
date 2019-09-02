package com.lambda.scheduler;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * // TODO Comment
 */
public class LambdaFunctionTest {

  private final String awsAccessKeyID = "-";
  private final String awsSecretAccessKey = "-";


  private AWSLambda initClient(String accessID, String accessKey) {
    Regions region = Regions.fromName("ap-southeast-1");

    BasicAWSCredentials credentials = new BasicAWSCredentials(accessID, accessKey);

    AWSLambdaAsyncClientBuilder builder = AWSLambdaAsyncClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(region);

    AWSLambda client = builder.build();
    return client;
  }

  private static String objectToJSON( Object obj, Logger logger) {
    String json = "";
    try {
      ObjectMapper mapper = new ObjectMapper();
      json = mapper.writeValueAsString(obj);
    } catch (IOException e) {
      logger.severe("Object to JSON failed: " + e.getLocalizedMessage());
    }
    return json;
  }


  @Test
  public void testlambdaFUnction(){

    final Logger logger = Logger.getLogger( this.getClass().getName());

    final String messageToLambda = "Johny";
    String lambdaMessageJSON = objectToJSON( messageToLambda, logger );


    AWSLambda lambdaClient = initClient(awsAccessKeyID, awsSecretAccessKey);

    InvokeRequest req = new InvokeRequest().withFunctionName("MethodHandlerLambda")
        .withPayload(lambdaMessageJSON);

    InvokeResult requestResult = lambdaClient.invoke(req);

    ByteBuffer byteBuff = requestResult.getPayload();

    if (byteBuff != null) {
      String result = StandardCharsets.UTF_8.decode(byteBuff).toString();
      logger.info("[INFO] testLambdaFunction::Lambda result: " + result);
    } else {
      logger.info("[INFO] testLambdaFunction: result payload is null");
    }
    
  }

  
}
