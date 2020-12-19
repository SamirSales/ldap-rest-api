package oi.github.samirsales.demoLDAP.domain.proxy;

import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserCreateInput;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserOutput;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.exception.IllegalValueException;

import java.util.List;
import java.util.UUID;

public interface UserProxy {
    UserOutput create(UserCreateInput input) throws IllegalValueException;

    List<UserOutput> getAll();

    void deleteById(UUID id) throws EntityNotFoundException;
}
