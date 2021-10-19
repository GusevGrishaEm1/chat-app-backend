package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.ban.BanInfoDto;
import ru.example.webapp.domain.dto.ban.BanInfoDtoRequest;
import ru.example.webapp.exception.BanInfoNotFoundException;
import ru.example.webapp.service.BanInfoService;
import java.util.List;

@RestController
@RequestMapping("/banInfo")
public class BanInfoController {

    @Autowired
    private BanInfoService banInfoService;

    @GetMapping()
    public List<BanInfoDto> getListBanInfo() throws BanInfoNotFoundException {
        return banInfoService.getListBanInfo();
    }

    @GetMapping("/{id}")
    public BanInfoDto getBanInfo(@PathVariable long id) throws BanInfoNotFoundException {
        return banInfoService.getBanInfo(id);
    }

    @DeleteMapping("/{id}")
    public long deleteBanInfo(@PathVariable long id) throws BanInfoNotFoundException {
        return banInfoService.deleteBanInfo(id);
    }

    @PostMapping()
    public BanInfoDto addBanInfo(@RequestBody BanInfoDtoRequest banInfo) {
        return banInfoService.addBanInfo(banInfo);
    }

    @PutMapping()
    public BanInfoDto editBanInfo(@RequestBody BanInfoDto banInfo) {
        return banInfoService.editBanInfo(banInfo);
    }

}
