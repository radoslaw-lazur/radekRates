package com.radekrates.controller;

import com.google.gson.Gson;
import com.radekrates.api.datafixerio.client.DataFixerClient;
import com.radekrates.controller.repository.AdminController;
import com.radekrates.domain.AdminRatio;
import com.radekrates.domain.dto.adminratio.AdminRatioDto;
import com.radekrates.mapper.AdminRatioMapper;
import com.radekrates.service.AdminRatioServiceDb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminRatioControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdminRatioServiceDb adminRatioServiceDb;
    @MockBean
    private AdminRatioMapper adminRatioMapper;
    private AdminRatio adminRatio;
    private AdminRatioDto adminRatioDto;

    @Before
    public void init() {
        adminRatio = new AdminRatio(
                1L,
                "123456789",
                LocalDate.of(2020, 3, 1),
                true,
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("3"),
                new BigDecimal("4"),
                new BigDecimal("5"),
                new BigDecimal("6"),
                new BigDecimal("7"),
                new BigDecimal("8"),
                new BigDecimal("9"),
                new BigDecimal("10"),
                new BigDecimal("11"),
                new BigDecimal("12"),
                new BigDecimal("13"),
                new BigDecimal("14"),
                new BigDecimal("15"),
                new BigDecimal("16"),
                new BigDecimal("17"),
                new BigDecimal("18"),
                new BigDecimal("19"),
                new BigDecimal("20")
        );
        adminRatioDto = new AdminRatioDto(
                1L,
                "123456789",
                LocalDate.of(2020, 3, 1),
                true,
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("3"),
                new BigDecimal("4"),
                new BigDecimal("5"),
                new BigDecimal("6"),
                new BigDecimal("7"),
                new BigDecimal("8"),
                new BigDecimal("9"),
                new BigDecimal("10"),
                new BigDecimal("11"),
                new BigDecimal("12"),
                new BigDecimal("13"),
                new BigDecimal("14"),
                new BigDecimal("15"),
                new BigDecimal("16"),
                new BigDecimal("17"),
                new BigDecimal("18"),
                new BigDecimal("19"),
                new BigDecimal("20")
        );
    }

    @Test
    public void shouldGetEmptyRatioSet() throws Exception {
        //Given
        Set<AdminRatioDto> adminRatioDtos = new HashSet<>();
        when(adminRatioMapper.mapToAdminRatioDtoSet(adminRatioServiceDb.getRatios())).thenReturn(adminRatioDtos);
        //When & Then
        mockMvc.perform(get("/v1/admin/getRatios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetRatios() throws Exception {
        //Given
        Set<AdminRatioDto> adminRatioDtos = new HashSet<>();
        adminRatioDtos.add(adminRatioDto);
        when(adminRatioMapper.mapToAdminRatioDtoSet(adminRatioServiceDb.getRatios())).thenReturn(adminRatioDtos);
        //When & Then
        mockMvc.perform(get("/v1/admin/getRatios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].key", is("123456789")))
                .andExpect(jsonPath("$[0].date", is("2020-03-01")))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[0].ratioEUR_PLN", is(1)))
                .andExpect(jsonPath("$[0].ratioEUR_GBP", is(2)))
                .andExpect(jsonPath("$[0].ratioEUR_CHF", is(3)))
                .andExpect(jsonPath("$[0].ratioEUR_USD", is(4)))
                .andExpect(jsonPath("$[0].ratioPLN_EUR", is(5)))
                .andExpect(jsonPath("$[0].ratioPLN_GBP", is(6)))
                .andExpect(jsonPath("$[0].ratioPLN_CHF", is(7)))
                .andExpect(jsonPath("$[0].ratioPLN_USD", is(8)))
                .andExpect(jsonPath("$[0].ratioGBP_EUR", is(9)))
                .andExpect(jsonPath("$[0].ratioGBP_PLN", is(10)))
                .andExpect(jsonPath("$[0].ratioGBP_CHF", is(11)))
                .andExpect(jsonPath("$[0].ratioGBP_USD", is(12)))
                .andExpect(jsonPath("$[0].ratioCHF_EUR", is(13)))
                .andExpect(jsonPath("$[0].ratioCHF_PLN", is(14)))
                .andExpect(jsonPath("$[0].ratioCHF_GBP", is(15)))
                .andExpect(jsonPath("$[0].ratioCHF_USD", is(16)))
                .andExpect(jsonPath("$[0].ratioUSD_EUR", is(17)))
                .andExpect(jsonPath("$[0].ratioUSD_PLN", is(18)))
                .andExpect(jsonPath("$[0].ratioUSD_GBP", is(19)))
                .andExpect(jsonPath("$[0].ratioUSD_CHF", is(20)));
    }

    //@Test
    public void shouldSaveRatios() throws Exception {
        //Given
        String jsonContent = new Gson().toJson(adminRatioDto);
        when(adminRatioServiceDb.saveAdminRatio(adminRatioMapper.mapToAdminRatio(adminRatioDto))).thenReturn(adminRatio);
        //When & Then
        mockMvc.perform(post("/v1/admin/saveRatios")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteAllRatios() throws Exception {
        //Given
        AdminRatioServiceDb adminRatioServiceDb = mock(AdminRatioServiceDb.class);
        doNothing().when(adminRatioServiceDb).deleteAll();
        //When & Then
        mockMvc.perform(delete("/v1/admin/deleteAllRatios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
