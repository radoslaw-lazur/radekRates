package com.radekrates.controller;

import com.google.gson.Gson;
import com.radekrates.controller.repository.IbanController;
import com.radekrates.domain.Iban;
import com.radekrates.domain.dto.iban.IbanDto;
import com.radekrates.domain.dto.iban.IbanToUserDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.mapper.IbanMapper;
import com.radekrates.service.IbanServiceDb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IbanController.class)
public class IbanControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IbanServiceDb ibanServiceDb;
    @MockBean
    private IbanMapper ibanMapper;
    private Iban iban;
    private IbanDto ibanDto;
    private UserEmailDto userEmailDto;

    @Before
    public void init() {
        iban = new Iban(
                1L,
                "bankName",
                "bankLocalisation",
                "PL",
                "PLN",
                "111111111111111111111111111111"
        );
        ibanDto = new IbanDto(
                1L,
                "bankName",
                "bankLocalisation",
                "PL",
                "PLN",
                "111111111111111111111111111111"
        );
        userEmailDto = new UserEmailDto(
                1L,
                "test@test.com"
        );
    }

    @Test
    public void shouldGetEmptyIbanSet() throws Exception {
        //Given
        Set<IbanDto> ibanDtos = new HashSet<>();
        when(ibanMapper.mapToIbanDtoSet(ibanServiceDb.getAllIbans())).thenReturn(ibanDtos);
        //When & Then
        mockMvc.perform(get("/v1/iban/getIbans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetIbanSet() throws Exception {
        //Given
        Set<IbanDto> ibanDtos = new HashSet<>();
        ibanDtos.add(ibanDto);
        when(ibanMapper.mapToIbanDtoSet(ibanServiceDb.getAllIbans())).thenReturn(ibanDtos);
        //When & Then
        mockMvc.perform(get("/v1/iban/getIbans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].bankName", is("bankName")))
                .andExpect(jsonPath("$[0].bankLocalisation", is("bankLocalisation")))
                .andExpect(jsonPath("$[0].countryCode", is("PL")))
                .andExpect(jsonPath("$[0].currencyCode", is("PLN")))
                .andExpect(jsonPath("$[0].ibanNumber", is("111111111111111111111111111111")));
    }

    @Test
    public void shouldGetIbanById() throws Exception {
        //Given
        Long ibanId = ibanDto.getId();
        when(ibanMapper.mapToIbanDto(ibanServiceDb.getIbanById(ibanId))).thenReturn(ibanDto);
        //When & Then
        mockMvc.perform(get("/v1/iban/getIban?ibanId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.bankName", is("bankName")))
                .andExpect(jsonPath("$.bankLocalisation", is("bankLocalisation")))
                .andExpect(jsonPath("$.countryCode", is("PL")))
                .andExpect(jsonPath("$.currencyCode", is("PLN")))
                .andExpect(jsonPath("$.ibanNumber", is("111111111111111111111111111111")));
    }

    @Test
    public void shouldGetIbansRelatedToUser() throws Exception {
        //Given
        Set<IbanDto> ibanDtos = new HashSet<>();
        ibanDtos.add(ibanDto);
        String jsonContent = new Gson().toJson(userEmailDto);
        when(ibanMapper.mapToIbanDtoSet(ibanServiceDb.getIbansRelatedToUser(userEmailDto))).thenReturn(ibanDtos);
        //When & Then
        mockMvc.perform(get("/v1/iban/getIbansRelatedToUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].bankName", is("bankName")))
                .andExpect(jsonPath("$[0].bankLocalisation", is("bankLocalisation")))
                .andExpect(jsonPath("$[0].countryCode", is("PL")))
                .andExpect(jsonPath("$[0].currencyCode", is("PLN")))
                .andExpect(jsonPath("$[0].ibanNumber", is("111111111111111111111111111111")));
    }

    @Test
    public void shouldSaveIban() throws Exception {
        //Given
        String jsonContent = new Gson().toJson(ibanDto);
        when(ibanServiceDb.saveIban(ibanMapper.mapToIban(ibanDto))).thenReturn(iban);
        //When & Then
        mockMvc.perform(post("/v1/iban/saveIban")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveIbanToUser() throws Exception {
        //Given
        doNothing().when(ibanServiceDb).saveIbanToUser(Mockito.isA(IbanToUserDto.class));
        String jsonContent = new Gson().toJson(new IbanToUserDto("test@test.com",
                "111111111111111111111111111111"));
        //When & Then
        mockMvc.perform(get("/v1/iban/saveIbanToUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateIban() throws Exception {
        //Given
        when(ibanMapper.mapToIbanDto(ibanServiceDb.saveIban(ibanMapper.mapToIban(ibanDto)))).thenReturn(ibanDto);
        String jsonContent = new Gson().toJson(ibanDto);
        //When & Then
        mockMvc.perform(put("/v1/iban/updateIban")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.bankName", is("bankName")))
                .andExpect(jsonPath("$.bankLocalisation", is("bankLocalisation")))
                .andExpect(jsonPath("$.countryCode", is("PL")))
                .andExpect(jsonPath("$.currencyCode", is("PLN")))
                .andExpect(jsonPath("$.ibanNumber", is("111111111111111111111111111111")));
    }

    @Test
    public void shouldDeleteIbanSet() throws Exception {
        //Given
        IbanServiceDb ibanServiceDb = mock(IbanServiceDb.class);
        doNothing().when(ibanServiceDb).deleteAllIbans();
        //When & Then
        mockMvc.perform(delete("/v1/iban/deleteAllIbans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteIbanById() throws Exception {
        //Given
        Long ibanId = ibanDto.getId();
        IbanServiceDb ibanServiceDb = mock(IbanServiceDb.class);
        doNothing().when(ibanServiceDb).deleteIbanById(ibanId);
        //When & Then
        mockMvc.perform(delete("/v1/iban/deleteIban?ibanId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
