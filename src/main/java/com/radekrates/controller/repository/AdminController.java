package com.radekrates.controller.repository;

import com.radekrates.api.datafixerio.client.DataFixerClient;
import com.radekrates.domain.dto.adminratio.AdminRatioDto;
import com.radekrates.domain.dto.datafixerio.DataFixerDto;
import com.radekrates.mapper.AdminRatioMapper;
import com.radekrates.service.AdminRatioServiceDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    private AdminRatioServiceDb adminRatioServiceDb;
    private AdminRatioMapper adminRatioMapper;

    @Autowired
    public AdminController(AdminRatioServiceDb adminRatioServiceDb,
                           AdminRatioMapper adminRatioMapper) {
        this.adminRatioServiceDb = adminRatioServiceDb;
        this.adminRatioMapper = adminRatioMapper;
    }

    @PostMapping(value = "saveRatios")
    public void saveRatios(@RequestBody AdminRatioDto adminRatioDto) {
        adminRatioServiceDb.saveAdminRatio(adminRatioMapper.mapToAdminRatio(adminRatioDto));
    }

    @GetMapping(value = "getRatios")
    public Set<AdminRatioDto> getRatios() {
        return adminRatioMapper.mapToAdminRatioDtoSet(adminRatioServiceDb.getRatios());
    }

    @DeleteMapping(value = "deleteAllRatios")
    public void deleteAllFalseRatios() {
        adminRatioServiceDb.deleteAll();
    }
}
