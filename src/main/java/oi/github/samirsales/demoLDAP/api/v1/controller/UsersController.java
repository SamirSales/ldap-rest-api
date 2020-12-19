package oi.github.samirsales.demoLDAP.api.v1.controller;

import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserCreateInput;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserOutput;
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
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UserOutput> create(@RequestBody UserCreateInput input) {
        UserOutput createdUser = this.userService.create(input);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserOutput>> getAll(){
        List<UserOutput> userOutputs = this.userService.getAll();
        return new ResponseEntity<>(userOutputs, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
