package ru.example.webapp.mapper;

import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.DiscInfoDtoRequest;
import java.util.List;

public interface DiscInfoMapper {

    public DiscInfoMapper INSTANCE = Mappers.getMapper( DiscInfoMapper.class );

    DiscInfoDto toDto(DiscInfo discInfo);

    List<DiscInfoDto> toDto(List<DiscInfo> discInfoList);

    DiscInfo toEntity(DiscInfoDto discInfo);

    DiscInfo toEntity(DiscInfoDtoRequest discInfo);
}
