package com.radekrates.controller.repository;

import com.radekrates.domain.dto.iban.IbanDto;
import com.radekrates.domain.dto.iban.IbanToUserDto;
import com.radekrates.mapper.IbanMapper;
import com.radekrates.service.IbanServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/iban")
public class IbanController {
    @Autowired
    private IbanMapper ibanMapper;
    @Autowired
    private IbanServiceDb ibanServiceDb;

    @PostMapping(value = "saveIban")
    public void saveIban(@RequestBody IbanDto ibanDto) {
        ibanServiceDb.saveIban(ibanMapper.mapToIban(ibanDto));
    }

    @GetMapping(value = "saveIbanToUser")
    public void saveIbanToUser(@RequestBody IbanToUserDto ibanToUserDto) {
        ibanServiceDb.saveIbanToUser(ibanToUserDto.getUserEmail(), ibanToUserDto.getIban());
    }

    @PutMapping(value = "updateIban")
    public IbanDto updateIban(@RequestBody IbanDto ibanDto) {
        return ibanMapper.mapToIbanDto(ibanServiceDb.saveIban(ibanMapper.mapToIban(ibanDto)));
    }

    @DeleteMapping(value = "deleteIban")
    public void deleteIban(@RequestParam Long ibanId) {
        ibanServiceDb.deleteIbanById(ibanId);
    }

    @GetMapping(value = "getIban")
    public IbanDto getIban(@RequestParam Long ibanId) {
        return ibanMapper.mapToIbanDto(ibanServiceDb.getIbanById(ibanId));
    }

    @GetMapping(value = "getIbans") 
    public Set<IbanDto> getIbans() {
        return ibanMapper.mapToIbanDtoSet(ibanServiceDb.getAllIbans());
    }

    @GetMapping(value = "getIbansRelatedToUser")
    public Set<IbanDto> getIbansRelatedToUser(@RequestParam String userEmail) {
        return ibanMapper.mapToIbanDtoSet(ibanServiceDb.getIbansRelatedToUser(userEmail));
    }

    @DeleteMapping(value = "deleteAllIbans")
    public void deleteAllIbans() {
        ibanServiceDb.deleteAllIbans();
    }
}
