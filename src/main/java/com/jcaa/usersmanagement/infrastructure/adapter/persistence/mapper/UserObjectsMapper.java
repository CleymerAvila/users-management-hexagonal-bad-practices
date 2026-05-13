package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserName;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.UserPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserObjectsMapper {

    UserObjectsMapper INSTANCE = Mappers.getMapper(UserObjectsMapper.class);


    // Model → DTO: extrae .value() de cada Value Object
    @Mapping(source = "idValue",       target = "id")
    @Mapping(source = "nameValue",     target = "name")
    @Mapping(source = "emailValue",    target = "email")
    @Mapping(source = "passwordValue", target = "password")
    @Mapping(source = "role",           target = "role",   qualifiedByName = "roleToString")
    @Mapping(source = "status",         target = "status", qualifiedByName = "statusToString")
    @Mapping(target = "createdAt",      ignore = true)
    @Mapping(target = "updatedAt",      ignore = true)
    UserPersistenceDto fromModelToUserPersistenceDto(UserModel user);


    // Entity → Model: construye Value Objects desde String
    @Mapping(source = "id",       target = "id",       qualifiedByName = "toUserId")
    @Mapping(source = "name",     target = "name",     qualifiedByName = "toUserName")
    @Mapping(source = "email",    target = "email",    qualifiedByName = "toUserEmail")
    @Mapping(source = "password", target = "password", qualifiedByName = "toUserPassword")
    @Mapping(source = "role",     target = "role",     qualifiedByName = "toUserRole")
    @Mapping(source = "status",   target = "status",   qualifiedByName = "toUserStatus")
    UserModel fromEntityToModel(UserEntity entity);

    @Named("roleToString")
    default String roleToString(UserRole role)     { return role.name(); }

    @Named("statusToString")
    default String statusToString(UserStatus status) { return status.name(); }

    @Named("toUserId")
    default UserId toUserId(String value) {
        return new UserId(value);
    }

    @Named("toUserName")
    default UserName toUserName(String value) {
        return new UserName(value);
    }

    @Named("toUserEmail")
    default UserEmail toUserEmail(String value) {
        return new UserEmail(value);
    }

    @Named("toUserPassword")
    default UserPassword toUserPassword(String value) {
        return UserPassword.fromHash(value);
    }

    @Named("toUserRole")
    default UserRole toUserRole(String value) {
        return UserRole.fromString(value);
    }

    @Named("toUserStatus")
    default UserStatus toUserStatus(String value) {
        return UserStatus.fromString(value);
    }
}
