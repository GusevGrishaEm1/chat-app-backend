package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.DiscInfoDtoRequest;
import ru.example.webapp.mapper.DiscInfoMapper;
import ru.example.webapp.repository.DiscInfoRepo;
import java.util.List;

@Service
public class DiscInfoService {

    @Autowired
    DiscInfoRepo discInfoRepo;

    DiscInfoMapper discInfoMapper;

    @Transactional
    public DiscInfoDto addDiscInfo(DiscInfoDtoRequest discInfoDtoRequest) {
        DiscInfo discInfoEntity = discInfoMapper.toEntity(discInfoDtoRequest);
        discInfoRepo.save(discInfoEntity);
        return discInfoMapper.toDto(discInfoEntity);
    }

    public long deleteDiscInfo(long id) {
        if (discInfoRepo.findById(id) == null)
            // add throw Exception
            return -1;
        else {
            discInfoRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public DiscInfoDto editDiscInfo(DiscInfoDto DiscInfoDto) {
        DiscInfo discInfoEntity = discInfoMapper.toEntity(DiscInfoDto);
        discInfoRepo.save(discInfoEntity);
        return discInfoMapper.toDto(discInfoEntity);
    }

    @Transactional(readOnly = true)
    public DiscInfoDto getDiscInfo(long id) {
        DiscInfo discInfoEntity = discInfoRepo.findById(id);
        if (discInfoEntity==null)
            // add throw Exception
            return null;
        else {
            return discInfoMapper.toDto(discInfoEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<DiscInfoDto> getListDiscInfo() {
        List<DiscInfo> discInfoList = discInfoRepo.findAll();
        if (discInfoList == null)
            // add throw Exception
            return null;
        else {
            return discInfoMapper.toDto(discInfoList);
        }
    }
}
