package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.dto.ban.BanInfoDto;
import ru.example.webapp.domain.dto.ban.BanInfoDtoRequest;
import ru.example.webapp.domain.dto.disc.DiscInfoDto;

import java.util.List;

@Mapper
public interface BanInfoMapper {

    BanInfoMapper INSTANCE = Mappers.getMapper( BanInfoMapper.class );

    @Mapping(source = "user.id" , target = "id" )
    BanInfoDto toDto(BanInfo banInfo);

    List<BanInfoDto> toDto(List<BanInfo> banInfoList);

    BanInfo toEntity(BanInfoDto banInfo);

    BanInfo toEntity(BanInfoDtoRequest banInfo);



}
