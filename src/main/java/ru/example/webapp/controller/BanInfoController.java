package ru.example.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.BanInfoDto;
import java.util.List;

@RestController
@RequestMapping("/banInfo")
public class BanInfoController {

    @GetMapping()
    public List<BanInfoDto> getListBanInfo() {
        return null;
    }

    @GetMapping("/{id}")
    public BanInfoDto getBanInfo(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long deleteBanInfo(@PathVariable long id) {
        return null;
    }

    @PostMapping()
    public BanInfoDto addBanInfo(@RequestBody BanInfoDto banInfo) {
        return null;
    }

    @PutMapping("/{id}")
    public BanInfoDto editBanInfo(@RequestBody BanInfoDto banInfo, @PathVariable Long id) {
        return null;
    }

}
