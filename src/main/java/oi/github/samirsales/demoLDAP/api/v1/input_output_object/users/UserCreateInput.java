package oi.github.samirsales.demoLDAP.api.v1.input_output_object.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateInput {
    private String name;
    private String surname;
}
