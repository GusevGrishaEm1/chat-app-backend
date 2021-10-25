package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.disc.DiscInfoDto;
import ru.example.webapp.domain.dto.disc.DiscInfoDtoRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-20T16:17:50+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class DiscInfoMapperImpl implements DiscInfoMapper {

    @Override
    public DiscInfoDto toDto(DiscInfo discInfo) {
        if ( discInfo == null ) {
            return null;
        }

        DiscInfoDto discInfoDto = new DiscInfoDto();

        discInfoDto.setId( discInfoUserInRoomId( discInfo ) );
        discInfoDto.setMinutes( discInfo.getMinutes() );
        discInfoDto.setDateOfDisc( discInfo.getDateOfDisc() );

        return discInfoDto;
    }

    @Override
    public List<DiscInfoDto> toDto(List<DiscInfo> discInfoList) {
        if ( discInfoList == null ) {
            return null;
        }

        List<DiscInfoDto> list = new ArrayList<DiscInfoDto>( discInfoList.size() );
        for ( DiscInfo discInfo : discInfoList ) {
            list.add( toDto( discInfo ) );
        }

        return list;
    }

    @Override
    public DiscInfo toEntity(DiscInfoDto discInfo) {
        if ( discInfo == null ) {
            return null;
        }

        DiscInfo discInfo1 = new DiscInfo();

        discInfo1.setId( discInfo.getId() );
        discInfo1.setMinutes( discInfo.getMinutes() );
        discInfo1.setDateOfDisc( discInfo.getDateOfDisc() );

        return discInfo1;
    }

    @Override
    public DiscInfo toEntity(DiscInfoDtoRequest discInfo) {
        if ( discInfo == null ) {
            return null;
        }

        DiscInfo discInfo1 = new DiscInfo();

        discInfo1.setMinutes( discInfo.getMinutes() );

        return discInfo1;
    }

    private long discInfoUserInRoomId(DiscInfo discInfo) {
        if ( discInfo == null ) {
            return 0L;
        }
        UserInRoom userInRoom = discInfo.getUserInRoom();
        if ( userInRoom == null ) {
            return 0L;
        }
        long id = userInRoom.getId();
        return id;
    }
}
