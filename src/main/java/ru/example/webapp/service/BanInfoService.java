package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.dto.BanInfoDto;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import ru.example.webapp.exception.BanInfoNotFoundException;
import ru.example.webapp.mapper.BanInfoMapper;
import ru.example.webapp.repository.BanInfoRepo;
import java.util.List;

@Service
public class BanInfoService {

    @Autowired
    BanInfoRepo banInfoRepo;

    BanInfoMapper banInfoMapper;

    @Transactional
    public BanInfoDto addBanInfo(BanInfoDtoRequest banInfoDtoRequest) {
        BanInfo banInfoEntity = banInfoMapper.toEntity(banInfoDtoRequest);
        banInfoRepo.save(banInfoEntity);
        return banInfoMapper.toDto(banInfoEntity);
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
        BanInfo banInfoEntity = banInfoMapper.toEntity(banInfoDto);
        banInfoRepo.save(banInfoEntity);
        return banInfoMapper.toDto(banInfoEntity);
    }

    @Transactional(readOnly = true)
    public BanInfoDto getBanInfo(long id) throws BanInfoNotFoundException {
        BanInfo banInfoEntity = banInfoRepo.findById(id);
        if (banInfoEntity==null)
            throw new BanInfoNotFoundException("BanInfo with id " + id + " not found");
        else {
            return banInfoMapper.toDto(banInfoEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<BanInfoDto> getListBanInfo()throws BanInfoNotFoundException {
        List<BanInfo> messages = banInfoRepo.findAll();
        if (messages.isEmpty())
            throw new BanInfoNotFoundException("BanInfoList is empty");
        else {
            return banInfoMapper.toDto(messages);
        }
    }
}