package com.radekrates.controller;

import com.google.gson.Gson;
import com.radekrates.controller.repository.TransactionController;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.dto.transaction.TransactionDto;
import com.radekrates.domain.dto.transaction.TransactionToProcessDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.mapper.TransactionMapper;
import com.radekrates.service.TransactionServiceDb;
import com.radekrates.service.transactionfactory.TransactionFactory;
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
@WebMvcTest(TransactionController.class)
public class TransactionControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionServiceDb transactionServiceDb;
    @MockBean
    private TransactionMapper transactionMapper;
    @MockBean
    private TransactionFactory transactionFactory;
    private Transaction transaction;
    private TransactionDto transactionDto;
    private TransactionToProcessDto transactionToProcessDto;
    private UserEmailDto userEmailDto;

    @Before
    public void init() {
        transaction = new Transaction(
                1L,
                "aaaa",
                "test@test.com",
                "inputIbanNumber",
                "outputIbanNumber",
                "PLN-EUR",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2020, 1, 1)
        );
        transactionDto = new TransactionDto(
                1L,
                "aaaa",
                "test@test.com",
                "inputIbanNumber",
                "outputIbanNumber",
                "PLN-EUR",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2020, 1, 1)
        );
        transactionToProcessDto = new TransactionToProcessDto(
                "test@test.com",
                "inputIbanNumber",
                "outputIbanNumber",
                "PLN-EUR",
                new BigDecimal("1")
        );
        userEmailDto = new UserEmailDto(
                1L,
                "test@test.com"
        );
    }

    @Test
    public void shouldGetEmptyTransactionSet() throws Exception {
        //Given
        Set<TransactionDto> transactionDtos = new HashSet<>();
        when(transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getAllTransactions())).thenReturn(transactionDtos);
        //When & Then
        mockMvc.perform(get("/v1/transaction/getTransactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetTransactionSet() throws Exception {
        //Given
        Set<TransactionDto> transactionDtos = new HashSet<>();
        transactionDtos.add(transactionDto);
        when(transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getAllTransactions())).thenReturn(transactionDtos);
        //When & Then
        mockMvc.perform(get("/v1/transaction/getTransactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].uniqueKeyChain", is("aaaa")))
                .andExpect(jsonPath("$[0].userEmail", is("test@test.com")))
                .andExpect(jsonPath("$[0].inputIbanNumber", is("inputIbanNumber")))
                .andExpect(jsonPath("$[0].outputIbanNumber", is("outputIbanNumber")))
                .andExpect(jsonPath("$[0].pairOfCurrencies", is("PLN-EUR")))
                .andExpect(jsonPath("$[0].inputValue", is(1)))
                .andExpect(jsonPath("$[0].outputValue", is(1)))
                .andExpect(jsonPath("$[0].apiCurrencyPurchaseMultiplier", is(1)))
                .andExpect(jsonPath("$[0].currencySaleMultiplier", is(1)))
                .andExpect(jsonPath("$[0].profit", is(1)))
                .andExpect(jsonPath("$[0].date", is("2020-01-01")));
    }

    @Test
    public void shouldGetTransactionbyId() throws Exception {
        //Given
        Long transactionId = transactionDto.getId();
        when(transactionMapper.mapToTransactionDto(transactionServiceDb.getTransactionById(transactionId))).thenReturn(transactionDto);
        //When & Then
        mockMvc.perform(get("/v1/transaction/getTransaction?transactionId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.uniqueKeyChain", is("aaaa")))
                .andExpect(jsonPath("$.userEmail", is("test@test.com")))
                .andExpect(jsonPath("$.inputIbanNumber", is("inputIbanNumber")))
                .andExpect(jsonPath("$.outputIbanNumber", is("outputIbanNumber")))
                .andExpect(jsonPath("$.pairOfCurrencies", is("PLN-EUR")))
                .andExpect(jsonPath("$.inputValue", is(1)))
                .andExpect(jsonPath("$.outputValue", is(1)))
                .andExpect(jsonPath("$.apiCurrencyPurchaseMultiplier", is(1)))
                .andExpect(jsonPath("$.currencySaleMultiplier", is(1)))
                .andExpect(jsonPath("$.profit", is(1)))
                .andExpect(jsonPath("$.date", is("2020-01-01")));
    }

    @Test
    public void shouldGetTransactionsRelatedToUser() throws Exception {
        //Given
        Set<TransactionDto> transactionDtos = new HashSet<>();
        transactionDtos.add(transactionDto);
        String jsonContent = new Gson().toJson(transactionDto);
        when(transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getTransactionsRelatedToUser(userEmailDto)))
                .thenReturn(transactionDtos);
        //When & Then
        mockMvc.perform(get("/v1/transaction/getTransactionsRelatedToUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].uniqueKeyChain", is("aaaa")))
                .andExpect(jsonPath("$[0].userEmail", is("test@test.com")))
                .andExpect(jsonPath("$[0].inputIbanNumber", is("inputIbanNumber")))
                .andExpect(jsonPath("$[0].outputIbanNumber", is("outputIbanNumber")))
                .andExpect(jsonPath("$[0].pairOfCurrencies", is("PLN-EUR")))
                .andExpect(jsonPath("$[0].inputValue", is(1)))
                .andExpect(jsonPath("$[0].outputValue", is(1)))
                .andExpect(jsonPath("$[0].apiCurrencyPurchaseMultiplier", is(1)))
                .andExpect(jsonPath("$[0].currencySaleMultiplier", is(1)))
                .andExpect(jsonPath("$[0].profit", is(1)))
                .andExpect(jsonPath("$[0].date", is("2020-01-01")));
    }

    @Test
    public void shouldSaveTransactionFromFactory() throws Exception {
        //Given
        String jsonContent = new Gson().toJson(transactionToProcessDto);
        doNothing().when(transactionServiceDb).saveTransactionToUser(Mockito.isA(TransactionToProcessDto.class));
        //When & Then
        mockMvc.perform(get("/v1/transaction/saveTransactionFromFactory")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTransactionSet() throws Exception {
        //Given
        TransactionServiceDb transactionServiceDb = mock(TransactionServiceDb.class);
        doNothing().when(transactionServiceDb).deleteAllTransactions();
        //When & Then
        mockMvc.perform(delete("/v1/transaction/deleteAllTransactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTransactionById() throws Exception {
        //Given
        Long transactionId = transactionDto.getId();
        TransactionServiceDb transactionServiceDb = mock(TransactionServiceDb.class);
        doNothing().when(transactionServiceDb).deleteTransactionById(transactionId);
        //When & Then
        mockMvc.perform(delete("/v1/transaction/deleteTransaction?transactionId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
