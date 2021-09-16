package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.DiscInfoDtoRequest;
import ru.example.webapp.service.DiscInfoService;
import java.util.List;

@RestController
@RequestMapping("/discInfo")
public class DiscInfoController {

    @Autowired
    DiscInfoService discInfoService;

    @GetMapping()
    public List<DiscInfoDto> getListDiscInfo() {
        return discInfoService.getListDiscInfo();
    }

    @GetMapping("/{id}")
    public DiscInfoDto getDiscInfo(@PathVariable long id) {
        return discInfoService.getDiscInfo(id);
    }

    @DeleteMapping("/{id}")
    public long deleteDiscInfo(@PathVariable long id) {
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
