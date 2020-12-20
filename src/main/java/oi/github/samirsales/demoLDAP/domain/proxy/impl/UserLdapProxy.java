package oi.github.samirsales.demoLDAP.domain.proxy.impl;

import oi.github.samirsales.demoLDAP.domain.entity.UserEntity;
import oi.github.samirsales.demoLDAP.domain.exception.EntityNotFoundException;
import oi.github.samirsales.demoLDAP.domain.exception.IllegalValueException;
import oi.github.samirsales.demoLDAP.domain.proxy.UserProxy;
import oi.github.samirsales.demoLDAP.utils.UserLdapAttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.List;
import java.util.UUID;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
@SuppressWarnings("unused")
public class UserLdapProxy implements UserProxy {

    public static final String BASE_DN = "dc=techinterview,dc=com";
    public static final String USERS_OU_DN = "ou=Users,dc=techinterview,dc=com";

    private final LdapTemplate ldapTemplate;

    public UserLdapProxy(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public UserEntity create(UserEntity userEntity) throws IllegalValueException {
        userEntity.setId(UUID.randomUUID());
        Name distinguishedName = getDistinguishedNameByUserId(userEntity.getId());
        ldapTemplate.bind(distinguishedName, null, getAttributesByUserEntity(userEntity));
        return userEntity;
    }

    private Attributes getAttributesByUserEntity(UserEntity userEntity) {
        BasicAttribute basicAttribute = new BasicAttribute("objectClass");
        basicAttribute.add("inetOrgPerson");
        basicAttribute.add("organizationalPerson");
        basicAttribute.add("person");
        basicAttribute.add("top");

        Attributes attributes = new BasicAttributes();
        attributes.put(basicAttribute);
        attributes.put("cn", userEntity.getName());
        attributes.put("sn", userEntity.getSurname());
        attributes.put("uid", userEntity.getId());

        return attributes;
    }

    @Override
    public List<UserEntity> getAll() {
        return ldapTemplate.search(query()
                        .base(USERS_OU_DN)
                        .where("objectClass")
                        .is("inetOrgPerson"),
                new UserLdapAttributesMapper());
    }

    @Override
    public void deleteById(UUID id) throws EntityNotFoundException {
        UserEntity user = this.getById(id);
        Name distinguishedName = getDistinguishedNameByUserId(id);
        ldapTemplate.unbind(distinguishedName);
    }

    private UserEntity getById(UUID id) throws EntityNotFoundException {
        List<UserEntity> users = ldapTemplate.search(query()
                        .base(USERS_OU_DN)
                        .where("uid")
                        .is(id.toString()),
                new UserLdapAttributesMapper());

        if (users.isEmpty()) {
            throw new EntityNotFoundException("User do not exist");
        }
        return users.get(0);
    }

    private Name getDistinguishedNameByUserId(UUID userId) {
        return LdapNameBuilder.newInstance(BASE_DN).add("ou", "Users").add("uid", userId).build();
    }

    // dn DistinguishedName
    // ou Organizational Unit
    // cn common name
    // sn surname
}
