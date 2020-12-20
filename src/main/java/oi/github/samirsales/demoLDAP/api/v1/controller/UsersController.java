package oi.github.samirsales.demoLDAP.api.v1.controller;

import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserCreateInput;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserIO;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserViewResponse;
import oi.github.samirsales.demoLDAP.api.v1.open_api.UsersControllerOpenApi;
import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import oi.github.samirsales.demoLDAP.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@SuppressWarnings("unused")
public class UsersController implements UsersControllerOpenApi {

    private final UserService userService;
    private final UserIO userIO;

    public UsersController(UserService userService, UserIO userIO) {
        this.userService = userService;
        this.userIO = userIO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public ResponseEntity<UserViewResponse> create(@RequestBody UserCreateInput input) {
        UserEntity userEntity = this.userIO.convertToEntity(input);
        UserEntity createdEntity = this.userService.create(userEntity);
        UserViewResponse createdUser = this.userIO.convertToViewResponse(createdEntity);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserViewResponse>> getAll() {
        List<UserEntity> userEntities = this.userService.getAll();
        List<UserViewResponse> userViewResponses = this.userIO.convertToViewResponseList(userEntities);
        return new ResponseEntity<>(userViewResponses, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
