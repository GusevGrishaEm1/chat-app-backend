package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.ban.BanInfoDto;
import ru.example.webapp.domain.dto.ban.BanInfoDtoRequest;
import ru.example.webapp.exception.BanInfoNotFoundException;
import ru.example.webapp.mapper.BanInfoMapper;
import ru.example.webapp.repository.BanInfoRepo;
import ru.example.webapp.repository.UserRepo;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BanInfoService {

    private BanInfoRepo banInfoRepo;
    private UserRepo userRepo;
    private BanInfoMapper banInfoMapper;

    @Autowired
    public BanInfoService(BanInfoRepo banInfoRepo, UserRepo userRepo) {
        this.banInfoRepo = banInfoRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public BanInfoDto addBanInfo(BanInfoDtoRequest banInfo) {
        BanInfo banInfoEntity = banInfoMapper.INSTANCE.toEntity(banInfo);
        User user = userRepo.findById(banInfo.getUserId());
        banInfoEntity.setDateOfBan(LocalDateTime.now());
        banInfoEntity.setUser(user);
        banInfoRepo.save(banInfoEntity);
        return banInfoMapper.INSTANCE.toDto(banInfoEntity);
    }

    public long deleteBanInfo(long id) throws BanInfoNotFoundException {
        if (banInfoRepo.findById(id) == null)
            throw new BanInfoNotFoundException("BanInfo with id " + id + " not found");
        else {
            banInfoRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public BanInfoDto editBanInfo(BanInfoDto banInfoDto) {
        BanInfo banInfoEntity = banInfoMapper.INSTANCE.toEntity(banInfoDto);
        banInfoRepo.save(banInfoEntity);
        return banInfoMapper.INSTANCE.toDto(banInfoEntity);
    }

    @Transactional(readOnly = true)
    public BanInfoDto getBanInfo(long id) throws BanInfoNotFoundException {
        BanInfo banInfoEntity = banInfoRepo.findById(id);
        if (banInfoEntity==null)
            throw new BanInfoNotFoundException("BanInfo with id " + id + " not found");
        else {
            return banInfoMapper.INSTANCE.toDto(banInfoEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<BanInfoDto> getListBanInfo()throws BanInfoNotFoundException {
        List<BanInfo> banInfoList = banInfoRepo.findAll();
        if (banInfoList.isEmpty())
            throw new BanInfoNotFoundException("BanInfoList is empty");
        else {
            return banInfoMapper.INSTANCE.toDto(banInfoList);
        }
    }
}
