package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.BanInfoDto;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-05T12:14:43+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class BanInfoMapperImpl implements BanInfoMapper {

    @Override
    public BanInfoDto toDto(BanInfo banInfo) {
        if ( banInfo == null ) {
            return null;
        }

        BanInfoDto banInfoDto = new BanInfoDto();

        banInfoDto.setId( banInfo.getId() );
        banInfoDto.setMinutes( banInfo.getMinutes() );
        banInfoDto.setDateOfBan( banInfo.getDateOfBan() );
        banInfoDto.setUser( userToUserDto( banInfo.getUser() ) );

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
        banInfo1.setUser( userDtoToUser( banInfo.getUser() ) );

        return banInfo1;
    }

    @Override
    public BanInfo toEntity(BanInfoDtoRequest banInfo) {
        if ( banInfo == null ) {
            return null;
        }

        BanInfo banInfo1 = new BanInfo();

        banInfo1.setMinutes( banInfo.getMinutes() );
        banInfo1.setDateOfBan( banInfo.getDateOfBan() );
        banInfo1.setUser( userDtoToUser( banInfo.getUser() ) );

        return banInfo1;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setBanned( user.isBanned() );
        userDto.setRole( user.getRole() );

        return userDto;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
        user.setBanned( userDto.isBanned() );
        user.setRole( userDto.getRole() );

        return user;
    }
}
