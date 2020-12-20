package oi.github.samirsales.demoLDAP.api.v1.open_api;

import io.swagger.annotations.*;
import oi.github.samirsales.demoLDAP.api.exception_handler.SwaggerDescriptionProblem;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserCreateInput;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserViewResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Api(tags = "Users")
@ApiResponses({
        @ApiResponse(code = 500, message = "There was an internal error", response = SwaggerDescriptionProblem.class)
})
@SuppressWarnings("unused")
public interface UsersControllerOpenApi {

    @ApiOperation("Create a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 400, message = "Invalid data", response = SwaggerDescriptionProblem.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",
                    response = SwaggerDescriptionProblem.class)
    })
    ResponseEntity<UserViewResponse> create(
            @ApiParam(name = "body", value = "Representation of a new user", required = true)
                    UserCreateInput userCreateInput);

    @ApiOperation("Get all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return list of users")
    })
    ResponseEntity<List<UserViewResponse>> getAll();

    @ApiOperation("Delete a user by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 400, message = "Invalid data", response = SwaggerDescriptionProblem.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",
                    response = SwaggerDescriptionProblem.class)
    })
    ResponseEntity<Void> deleteById(
            @ApiParam(value = "User id", example = "b31b5ff7-cb47-42dc-8ad9-8de61755173c", required = true)
                    UUID id);
}
