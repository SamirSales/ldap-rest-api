package oi.github.samirsales.demoLDAP.api.v1.input_output_object.users;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserOutput {
    private UUID uid;
    private String cn;
    private String sn;
}