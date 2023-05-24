package com.edu.security.Controller;

import com.edu.security.Mapper.CakeMapper;
import com.edu.security.Models.DTO.CakeDTO;
import com.edu.security.Repository.CakeRepository;
import com.edu.security.Service.CakeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cake")
@RequiredArgsConstructor
public class CakeController {

    private final CakeService cakeService;
    private final CakeMapper cakeMapper;

    @PostMapping
    public CakeDTO saveCake(@Valid @RequestBody CakeDTO dto) {
        return cakeMapper.map(cakeService.save(cakeMapper.map(dto)));
    }

    @GetMapping
    public CakeDTO getCakeById(@RequestParam UUID id) {
        return cakeMapper.map(cakeService.getCakeById(id));
    }


}
