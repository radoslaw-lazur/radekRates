package com.radekrates.controller.repository;

import com.radekrates.domain.dto.iban.IbanDto;
import com.radekrates.domain.dto.iban.IbanToUserDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.mapper.IbanMapper;
import com.radekrates.service.IbanServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class IbanController {
    private IbanMapper ibanMapper;
    private IbanServiceDb ibanServiceDb;

    @Autowired
    public IbanController(IbanMapper ibanMapper, IbanServiceDb ibanServiceDb) {
        this.ibanMapper = ibanMapper;
        this.ibanServiceDb = ibanServiceDb;
    }

    @PostMapping(value = "/ibans")
    public void saveIban(@RequestBody IbanDto ibanDto) {
        ibanServiceDb.saveIban(ibanMapper.mapToIban(ibanDto));
    }

    @PostMapping(value = "/ibanToUser")
    public void saveIbanToUser(@RequestBody IbanToUserDto ibanToUserDto) {
        ibanServiceDb.saveIbanToUser(ibanToUserDto);
    }

    @PostMapping(value = "/ibansUser")
    public Set<IbanDto> getIbansRelatedToUser(@RequestBody UserEmailDto userEmailDto) {
        return ibanMapper.mapToIbanDtoSet(ibanServiceDb.getIbansRelatedToUser(userEmailDto));
    }

    @PutMapping(value = "/ibans")
    public IbanDto updateIban(@RequestBody IbanDto ibanDto) {
        return ibanMapper.mapToIbanDto(ibanServiceDb.updateIban(ibanMapper.mapToIban(ibanDto)));
    }

    @GetMapping(value = "/ibans/{ibanId}")
    public IbanDto getIban(@PathVariable Long ibanId) {
        return ibanMapper.mapToIbanDto(ibanServiceDb.getIbanById(ibanId));
    }

    @GetMapping(value = "/ibans")
    public Set<IbanDto> getIbans() {
        return ibanMapper.mapToIbanDtoSet(ibanServiceDb.getAllIbans());
    }

    @DeleteMapping(value = "/ibans/{ibanId}")
    public void deleteIban(@PathVariable Long ibanId) {
        ibanServiceDb.deleteIbanById(ibanId);
    }

    @DeleteMapping(value = "/ibans")
    public void deleteAllIbans() {
        ibanServiceDb.deleteAllIbans();
    }
}
