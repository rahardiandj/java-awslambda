package com.lambda.scheduler.scheduler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * // TODO Comment
 */
public class LambdaRequestHandler implements RequestHandler<String, String> {
  public String handleRequest(String input, Context context) {
    context.getLogger().log("Input: " + input);
    return "Your input is : " + input;
  }
}
