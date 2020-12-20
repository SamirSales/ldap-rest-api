package oi.github.samirsales.demoLDAP.utils;

import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.UUID;

public class UserLdapAttributesMapper implements AttributesMapper<UserEntity> {

    @Override
    public UserEntity mapFromAttributes(Attributes attributes) throws NamingException {
        UserEntity user = new UserEntity();
        user.setId(attributes.get("uid") != null ? UUID.fromString(attributes.get("uid").get().toString()) : null);
        user.setName(attributes.get("cn") != null ? attributes.get("cn").get().toString() : null);
        user.setSurname(attributes.get("sn") != null ? attributes.get("sn").get().toString() : null);
        return user;
    }
}