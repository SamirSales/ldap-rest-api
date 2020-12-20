package oi.github.samirsales.demoLDAP.domain.service;

import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.proxy.UserMockProxy;
import oi.github.samirsales.demoLDAP.domain.proxy.UserProxy;
import oi.github.samirsales.demoLDAP.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    private UserService userService;

    @BeforeEach
    public void resetUserService() {
        UserProxy userProxy = new UserMockProxy();
        userService = new UserServiceImpl(userProxy);
    }

    @Test
    void createUser() {
        UserEntity userToCreate = this.getUserEntityExample();
        UserEntity createdUser = this.userService.create(userToCreate);

        assertEquals(userToCreate.getName(), createdUser.getName());
        assertEquals(userToCreate.getSurname(), createdUser.getSurname());
    }

    @Test
    void getAllUsers() {
        assertNotNull(this.userService.getAll());
    }

    @Test
    void deleteUserById() {
        UserEntity createdUser = this.userService.create(this.getUserEntityExample());
        this.userService.deleteById(createdUser.getId());
        List<UserEntity> userEntities = this.userService.getAll();

        boolean isEntityNotFound = userEntities.stream()
                .noneMatch(userEntity -> userEntity.getId().equals(createdUser.getId()));
        assertTrue(isEntityNotFound);
    }

    @Test
    void deleteUserByIdAndThrowsEntityNotFoundException() {
        UUID randomUUID = UUID.randomUUID();
        assertThrows(EntityNotFoundException.class, () -> this.userService.deleteById(randomUUID));
    }

    private UserEntity getUserEntityExample() {
        UserEntity userEntityExample = new UserEntity();
        userEntityExample.setId(null);
        userEntityExample.setName("Samir");
        userEntityExample.setSurname("Sales");
        return userEntityExample;
    }
}
