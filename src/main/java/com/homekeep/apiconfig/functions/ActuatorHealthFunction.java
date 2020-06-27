package com.homekeep.apiconfig.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

public class ActuatorHealthFunction extends AzureSpringBootRequestHandler {

    @FunctionName("health")
    public HttpResponseMessage getHealth(@HttpTrigger(name = "getHealth", route = "/actuator/health",
            methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Void> request,
                                         ExecutionContext context) {

        return request.createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(context))
                .header("Content-Type", "application/json")
                .build();

    }
}
