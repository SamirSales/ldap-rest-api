package oi.github.samirsales.demoLDAP.domain.service.impl;

import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserCreateInput;
import oi.github.samirsales.demoLDAP.api.v1.input_output_object.users.UserOutput;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.exception.IllegalValueException;
import oi.github.samirsales.demoLDAP.domain.proxy.UserProxy;
import oi.github.samirsales.demoLDAP.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    private final UserProxy userProxy;

    public UserServiceImpl(UserProxy userProxy){
        this.userProxy = userProxy;
    }

    @Override
    public UserOutput create(UserCreateInput input) throws IllegalValueException {
        return this.userProxy.create(input);
    }

    @Override
    public List<UserOutput> getAll() {
        return this.userProxy.getAll();
    }

    @Override
    public void deleteById(UUID id) throws EntityNotFoundException {
        this.userProxy.deleteById(id);
    }
}
