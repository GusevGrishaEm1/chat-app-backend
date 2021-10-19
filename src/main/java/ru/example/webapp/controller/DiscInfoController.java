package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.disc.DiscInfoDto;
import ru.example.webapp.domain.dto.disc.DiscInfoDtoRequest;
import ru.example.webapp.exception.DiscInfoNotFoundException;
import ru.example.webapp.service.DiscInfoService;
import java.util.List;

@RestController
@RequestMapping("/discInfo")
public class DiscInfoController {

    @Autowired
    private DiscInfoService discInfoService;

    @GetMapping()
    public List<DiscInfoDto> getListDiscInfo() throws DiscInfoNotFoundException {
        return discInfoService.getListDiscInfo();
    }

    @GetMapping("/{id}")
    public DiscInfoDto getDiscInfo(@PathVariable long id) throws DiscInfoNotFoundException {
        return discInfoService.getDiscInfo(id);
    }

    @DeleteMapping("/{id}")
    public long deleteDiscInfo(@PathVariable long id) throws DiscInfoNotFoundException {
        return discInfoService.deleteDiscInfo(id);
    }

    @PostMapping()
    public DiscInfoDto addDiscInfo(@RequestBody DiscInfoDtoRequest discInfo) {
        return discInfoService.addDiscInfo(discInfo);
    }

    @PutMapping()
    public DiscInfoDto editDiscInfo(@RequestBody DiscInfoDto discInfo) {
        return discInfoService.editDiscInfo(discInfo);
    }

}
