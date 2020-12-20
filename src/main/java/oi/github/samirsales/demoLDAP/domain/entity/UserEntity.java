package oi.github.samirsales.demoLDAP.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserEntity {
    private UUID id;
    private String name;
    private String surname;
}
