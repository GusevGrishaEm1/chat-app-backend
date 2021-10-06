package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.dto.BanInfoDto;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import java.util.List;

@Mapper
public interface BanInfoMapper {

    BanInfoMapper INSTANCE = Mappers.getMapper( BanInfoMapper.class );

    BanInfoDto toDto(BanInfo banInfo);

    List<BanInfoDto> toDto(List<BanInfo> banInfoList);

    BanInfo toEntity(BanInfoDto banInfo);

    BanInfo toEntity(BanInfoDtoRequest banInfo);



}
