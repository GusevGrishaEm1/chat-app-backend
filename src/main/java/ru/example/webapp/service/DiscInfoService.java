package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.DiscInfoDtoRequest;
import ru.example.webapp.exception.DiscInfoNotFoundException;
import ru.example.webapp.mapper.DiscInfoMapper;
import ru.example.webapp.repository.DiscInfoRepo;
import java.util.List;

@Service
public class DiscInfoService {

    @Autowired
    private DiscInfoRepo discInfoRepo;

    private DiscInfoMapper discInfoMapper;

    @Transactional
    public DiscInfoDto addDiscInfo(DiscInfoDtoRequest discInfoDtoRequest) {
        DiscInfo discInfoEntity = discInfoMapper.INSTANCE.toEntity(discInfoDtoRequest);
        discInfoRepo.save(discInfoEntity);
        return discInfoMapper.INSTANCE.toDto(discInfoEntity);
    }

    public long deleteDiscInfo(long id) throws DiscInfoNotFoundException {
        if (discInfoRepo.findById(id) == null)
            throw new DiscInfoNotFoundException("DiscInfo with id " + id + " not found");
        else {
            discInfoRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public DiscInfoDto editDiscInfo(DiscInfoDto DiscInfoDto) {
        DiscInfo discInfoEntity = discInfoMapper.INSTANCE.toEntity(DiscInfoDto);
        discInfoRepo.save(discInfoEntity);
        return discInfoMapper.INSTANCE.toDto(discInfoEntity);
    }

    @Transactional(readOnly = true)
    public DiscInfoDto getDiscInfo(long id) throws DiscInfoNotFoundException {
        DiscInfo discInfoEntity = discInfoRepo.findById(id);
        if (discInfoEntity==null)
            throw new DiscInfoNotFoundException("DiscInfo with id " + id + "not found");
        else {
            return discInfoMapper.INSTANCE.toDto(discInfoEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<DiscInfoDto> getListDiscInfo() throws DiscInfoNotFoundException {
        List<DiscInfo> discInfoList = discInfoRepo.findAll();
        if (discInfoList.isEmpty())
            throw new DiscInfoNotFoundException("DiscInfoList is empty");
        else {
            return discInfoMapper.INSTANCE.toDto(discInfoList);
        }
    }
}
