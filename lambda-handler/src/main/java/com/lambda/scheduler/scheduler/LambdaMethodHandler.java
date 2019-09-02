package com.lambda.scheduler.scheduler;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * // TODO Comment
 */
public class LambdaMethodHandler {
  public String handleRequest(String input, Context context) {
    context.getLogger().log("Input: " + input);
    return "Your input is : " + input;
  }
}
