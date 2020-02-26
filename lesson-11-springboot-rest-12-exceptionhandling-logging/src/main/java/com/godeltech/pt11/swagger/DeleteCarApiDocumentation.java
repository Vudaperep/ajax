package com.godeltech.pt11.swagger;

import io.swagger.annotations.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiOperation("Delete car")
@ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id", value = "car's id", required = true, dataType = "Long", paramType = "path", example = "1")
})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Request passed"),
        @ApiResponse(code = 400, message = "Invalid data supplied"),
        @ApiResponse(code = 404, message = "Resource not found"),
        @ApiResponse(code = 500, message = "Server error")
})
public @interface DeleteCarApiDocumentation {
}
