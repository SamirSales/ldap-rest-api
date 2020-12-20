package oi.github.samirsales.demoLDAP.api.v1.input_output_object.users;

import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserIO {

    public UserEntity convertToEntity(UserCreateInput userCreateInput) {
        UserEntity entity = new UserEntity();
        entity.setId(null);
        entity.setName(userCreateInput.getName());
        entity.setSurname(userCreateInput.getSurname());
        return entity;
    }

    public List<UserViewResponse> convertToViewResponseList(List<UserEntity> entities) {
        return entities.stream().map(this::convertToViewResponse).collect(Collectors.toList());
    }

    public UserViewResponse convertToViewResponse(UserEntity userEntity) {
        UserViewResponse viewResponse = new UserViewResponse();
        viewResponse.setId(userEntity.getId());
        viewResponse.setName(userEntity.getName());
        viewResponse.setSurname(userEntity.getSurname());
        return viewResponse;
    }
}
