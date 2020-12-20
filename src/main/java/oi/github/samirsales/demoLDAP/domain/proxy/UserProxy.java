package oi.github.samirsales.demoLDAP.domain.proxy;

import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.exception.IllegalValueException;

import java.util.List;
import java.util.UUID;

public interface UserProxy {
    UserEntity create(UserEntity userEntity) throws IllegalValueException;

    List<UserEntity> getAll();

    void deleteById(UUID id) throws EntityNotFoundException;
}
