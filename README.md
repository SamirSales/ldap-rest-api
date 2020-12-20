# LDAP Rest API
An application to manage users in an LDAP server.

## OpenLDAP Server Configuration
Docker image: [docker-openldap](https://github.com/osixia/docker-openldap)

Container execution:
```
docker run --env LDAP_ORGANISATION="My Company" --env LDAP_DOMAIN="techinterview.com" --env LDAP_ADMIN_PASSWORD="123456" -p 389:389 --detach osixia/openldap:1.4.0
```

Create the OU user under domain by creation of the file **create_ou_users.ldif** with the following content:

```
dn: ou=Users,dc=techinterview,dc=com
changetype: add
objectClass: organizationalUnit
objectClass: top
ou: Users
<blank line at the end of the file>
```

Create an OU (organizationalUnit) to hold your users by the following command:

```
ldapmodify -h localhost -p 389 -w '123456' -D 'cn=admin,dc=techinterview,dc=com' < create_ou_users.ldif
```

## Swagger
[http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)

## License
- MIT

