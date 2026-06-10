package org.nessrev.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.nessrev.userservice.dto.UserRequest;
import org.nessrev.userservice.dto.UserResponse;
import org.nessrev.userservice.entity.User;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
  UserResponse toResponse(User user);
  User toEntity(UserRequest request);
  void updateUserFromDto(UserRequest request, @MappingTarget User user);
}