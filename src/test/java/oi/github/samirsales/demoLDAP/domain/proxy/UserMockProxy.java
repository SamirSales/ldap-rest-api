package oi.github.samirsales.demoLDAP.domain.proxy;

import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserMockProxy implements UserProxy {

    private final List<UserEntity> entityList;

    public UserMockProxy() {
        this.entityList = new ArrayList<>();
    }

    @Override
    public UserEntity create(UserEntity userEntity) throws IllegalValueException {
        UserEntity createdEntity = new UserEntity();
        createdEntity.setId(UUID.randomUUID());
        createdEntity.setName(userEntity.getName());
        createdEntity.setSurname(userEntity.getSurname());
        this.entityList.add(createdEntity);
        return createdEntity;
    }

    @Override
    public List<UserEntity> getAll() {
        return this.entityList;
    }

    @Override
    public void deleteById(UUID id) throws EntityNotFoundException {
        boolean isEntityNotFound = this.entityList.stream().noneMatch(userEntity -> userEntity.getId().equals(id));

        if (isEntityNotFound) {
            throw new EntityNotFoundException("User not found");
        } else {
            for (int i = 0; i < this.entityList.size(); i++) {
                if (this.entityList.get(i).getId().equals(id)) {
                    this.entityList.remove(i);
                    break;
                }
            }
        }
    }
}
