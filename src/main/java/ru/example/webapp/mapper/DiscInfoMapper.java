package ru.example.webapp.mapper;

import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.DiscInfoDtoRequest;

import java.util.List;

public interface DiscInfoMapper {
    DiscInfoDto toDto(DiscInfo discInfo);

    List<DiscInfoDto> toDto(List<DiscInfo> discInfoList);

    DiscInfo toEntity(DiscInfoDto discInfo);

    DiscInfo toEntity(DiscInfoDtoRequest discInfo);
}
