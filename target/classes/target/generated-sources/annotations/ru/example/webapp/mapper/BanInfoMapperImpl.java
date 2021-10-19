package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.ban.BanInfoDto;
import ru.example.webapp.domain.dto.ban.BanInfoDtoRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-19T15:34:10+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class BanInfoMapperImpl implements BanInfoMapper {

    @Override
    public BanInfoDto toDto(BanInfo banInfo) {
        if ( banInfo == null ) {
            return null;
        }

        BanInfoDto banInfoDto = new BanInfoDto();

        banInfoDto.setId( banInfoUserId( banInfo ) );
        banInfoDto.setMinutes( banInfo.getMinutes() );
        banInfoDto.setDateOfBan( banInfo.getDateOfBan() );

        return banInfoDto;
    }

    @Override
    public List<BanInfoDto> toDto(List<BanInfo> banInfoList) {
        if ( banInfoList == null ) {
            return null;
        }

        List<BanInfoDto> list = new ArrayList<BanInfoDto>( banInfoList.size() );
        for ( BanInfo banInfo : banInfoList ) {
            list.add( toDto( banInfo ) );
        }

        return list;
    }

    @Override
    public BanInfo toEntity(BanInfoDto banInfo) {
        if ( banInfo == null ) {
            return null;
        }

        BanInfo banInfo1 = new BanInfo();

        banInfo1.setId( banInfo.getId() );
        banInfo1.setMinutes( banInfo.getMinutes() );
        banInfo1.setDateOfBan( banInfo.getDateOfBan() );

        return banInfo1;
    }

    @Override
    public BanInfo toEntity(BanInfoDtoRequest banInfo) {
        if ( banInfo == null ) {
            return null;
        }

        BanInfo banInfo1 = new BanInfo();

        banInfo1.setMinutes( banInfo.getMinutes() );

        return banInfo1;
    }

    private long banInfoUserId(BanInfo banInfo) {
        if ( banInfo == null ) {
            return 0L;
        }
        User user = banInfo.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }
}
