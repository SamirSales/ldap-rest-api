package oi.github.samirsales.demoLDAP.domain.proxy.impl;

import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserCreateInput;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserOutput;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.exception.IllegalValueException;
import oi.github.samirsales.demoLDAP.domain.proxy.UserProxy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@SuppressWarnings("unused")
public class UserLdapProxy implements UserProxy {

    @Override
    public UserOutput create(UserCreateInput input) throws IllegalValueException {
        return null;
    }

    @Override
    public List<UserOutput> getAll() {
        return null;
    }

    @Override
    public void deleteById(UUID id) throws EntityNotFoundException {

    }
}
