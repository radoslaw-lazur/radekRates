package com.radekrates.controller.repository;

import com.radekrates.domain.dto.adminratio.AdminRatioDto;
import com.radekrates.mapper.AdminRatioMapper;
import com.radekrates.service.AdminRatioServiceDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class AdminController {
    private AdminRatioServiceDb adminRatioServiceDb;
    private AdminRatioMapper adminRatioMapper;

    @Autowired
    public AdminController(AdminRatioServiceDb adminRatioServiceDb,
                           AdminRatioMapper adminRatioMapper) {
        this.adminRatioServiceDb = adminRatioServiceDb;
        this.adminRatioMapper = adminRatioMapper;
    }

    @PostMapping(value = "/ratios")
    public void saveRatios(@RequestBody AdminRatioDto adminRatioDto) {
        adminRatioServiceDb.saveAdminRatio(adminRatioMapper.mapToAdminRatio(adminRatioDto));
    }

    @GetMapping(value = "/ratios")
    public Set<AdminRatioDto> getRatios() {
        return adminRatioMapper.mapToAdminRatioDtoSet(adminRatioServiceDb.getRatios());
    }

    @DeleteMapping(value = "/ratios")
    public void deleteAllRatios() {
        adminRatioServiceDb.deleteAll();
    }
}
