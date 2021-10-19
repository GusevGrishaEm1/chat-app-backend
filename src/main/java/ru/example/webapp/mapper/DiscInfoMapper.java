package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.dto.disc.DiscInfoDto;
import ru.example.webapp.domain.dto.disc.DiscInfoDtoRequest;
import ru.example.webapp.domain.dto.message.MessageDto;

import java.util.List;
@Mapper
public interface DiscInfoMapper {

    DiscInfoMapper INSTANCE = Mappers.getMapper( DiscInfoMapper.class );

    @Mapping(source = "userInRoom.id" , target = "id" )
    DiscInfoDto toDto(DiscInfo discInfo);

    List<DiscInfoDto> toDto(List<DiscInfo> discInfoList);

    DiscInfo toEntity(DiscInfoDto discInfo);

    DiscInfo toEntity(DiscInfoDtoRequest discInfo);

}
