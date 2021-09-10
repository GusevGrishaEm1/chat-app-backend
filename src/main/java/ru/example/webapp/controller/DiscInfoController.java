package ru.example.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.DiscInfoDto;
import java.util.List;

@RestController
@RequestMapping("/discInfo")
public class DiscInfoController {

    @GetMapping()
    public List<DiscInfoDto> getListDiscInfo() {
        return null;
    }

    @GetMapping("/{id}")
    public DiscInfoDto getDiscInfo(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long deleteDiscInfo(@PathVariable long id) {
        return null;
    }

    @PostMapping()
    public DiscInfoDto addDiscInfo(@RequestBody DiscInfoDto discInfo) {
        return null;
    }

    @PutMapping("/{id}")
    public DiscInfoDto editDiscInfo(@RequestBody DiscInfoDto discInfo, @PathVariable Long id) {
        return null;
    }

}
