package pl.piasta.camperoo.user.domain;

import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.user.domain.UserConverter.PersonConverter;
import pl.piasta.camperoo.user.domain.vo.PhoneNumber;
import pl.piasta.camperoo.user.domain.vo.ZipCode;
import pl.piasta.camperoo.user.dto.CreateAccountDto;

@Mapper(uses = {PersonConverter.class, EmailAddress.class})
@Setter(onMethod_ = @Autowired)
abstract class UserConverter {
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "email", source = "email")
    @Mapping(target = "passwordHash", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    @Mapping(target = "person", source = ".")
    abstract User convert(CreateAccountDto dto);

    @Mapper(uses = {ZipCode.class, PhoneNumber.class})
    interface PersonConverter {
        Person convert(CreateAccountDto dto);
    }
}
