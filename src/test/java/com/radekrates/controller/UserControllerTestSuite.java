package com.radekrates.controller;

import com.radekrates.controller.repository.UserController;
import com.radekrates.domain.User;
import com.radekrates.domain.dto.iban.IbanDto;
import com.radekrates.domain.dto.transaction.TransactionDto;
import com.radekrates.domain.dto.user.UserDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.domain.dto.user.UserLogInDto;
import com.radekrates.domain.dto.user.UserLoggedInDto;
import com.radekrates.mapper.UserMapper;
import com.radekrates.service.UserServiceDb;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.is;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceDb userServiceDb;
    @MockBean
    private UserMapper userMapper;
    private User user;
    private UserDto userDto;
    private UserEmailDto userEmailDto;
    private UserLogInDto userLogInDto;

    @Before
    public void init() {
        user = new User(1L, "test@test.com","password", "Radoslaw",
                "Lazur", 30, "Poland", "code", true, false);
        userDto = new UserDto(1L, "test@test.com","password", "Radoslaw",
                "Lazur", 30, "Poland", "code", true, false);
        userEmailDto = new UserEmailDto(1L, "test@test.com");
        userLogInDto = new UserLogInDto(1L, "test@test.com", "password");
    }

    @Test
    public void shouldGetEmptyUserSet() throws Exception {
        //Given
        Set<UserDto> userDtos = new HashSet<>();
        when(userMapper.mapToUserDtoSet(userServiceDb.getAllUsers())).thenReturn(userDtos);
        //When & Then
        mockMvc.perform(get("/v1/user/getUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetUserSet() throws Exception {
        Set<UserDto> userDtos = new HashSet<>();
        userDtos.add(userDto);
        when(userMapper.mapToUserDtoSet(userServiceDb.getAllUsers())).thenReturn(userDtos);
        mockMvc.perform(get("/v1/user/getUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("test@test.com")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].userFirstName", is("Radoslaw")))
                .andExpect(jsonPath("$[0].userLastName", is("Lazur")))
                .andExpect(jsonPath("$[0].age", is(30)))
                .andExpect(jsonPath("$[0].country", is("Poland")))
                .andExpect(jsonPath("$[0].activationCode", is("code")))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[0].blocked", is(false)));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        //Given
        Long userId = userDto.getId();
        when(userMapper.mapToUserDto(userServiceDb.getUserById(userId))).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/user/getUser?userId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.password", is("password")))
                .andExpect(jsonPath("$.userFirstName", is("Radoslaw")))
                .andExpect(jsonPath("$.userLastName", is("Lazur")))
                .andExpect(jsonPath("$.age", is(30)))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.activationCode", is("code")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.blocked", is(false)));
    }

    @Test
    public void shouldSaveUser() throws Exception {
        //Given
        when(userServiceDb.saveUser(userMapper.mapToUser(userDto))).thenReturn(user);
        String jsonContent = new Gson().toJson(userDto);
        //When & Then
        mockMvc.perform(post("/v1/user/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        when(userMapper.mapToUserDto(userServiceDb.saveUser(userMapper.mapToUser(userDto)))).thenReturn(userDto);
        String jsonContent = new Gson().toJson(userDto);
        //When & Then
        mockMvc.perform(put("/v1/user/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.password", is("password")))
                .andExpect(jsonPath("$.userFirstName", is("Radoslaw")))
                .andExpect(jsonPath("$.userLastName", is("Lazur")))
                .andExpect(jsonPath("$.age", is(30)))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.activationCode", is("code")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.blocked", is(false)));
    }

    @Test
    public void shouldActivateUser() throws Exception {
        //Given
        String activationCode = userDto.getActivationCode();
        when(userServiceDb.activateUser(activationCode)).thenReturn(true);
        //When & Then
        mockMvc.perform(get("/v1/user/activateUser?activationCode=code")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void shouldBlockUser() throws Exception {
        //Given
        UserServiceDb userServiceDb = mock(UserServiceDb.class);
        doNothing().when(userServiceDb).blockUser(userEmailDto);
        String jsonContent = new Gson().toJson(userEmailDto);
        //When & Then
        mockMvc.perform(post("/v1/user/blockUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetDataRelatedToUser() throws Exception {
        //Given
        Set<IbanDto> ibans = new HashSet<>();
        ibans.add(new IbanDto(1L, "bankName", "bankLocalisation", "PL",
                "PLN", "111111111111111111111111111111"));
        Set<TransactionDto> transactions = new HashSet<>();
        transactions.add(new TransactionDto(
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
                LocalDate.of(2020, 1, 1)));
        UserLoggedInDto userLoggedInDto = new UserLoggedInDto("test@test.com",
                "Radoslaw", "Lazur", ibans, transactions);
        String jsonContent = new Gson().toJson(userLoggedInDto);
        when(userServiceDb.getDataRetaltedToUser(Mockito.isA(UserLogInDto.class))).thenReturn(userLoggedInDto);
        //When & Then
        mockMvc.perform(post("/v1/user/getDataRelatedToUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail", is("test@test.com")))
                .andExpect(jsonPath("$.userFirstName", is("Radoslaw")))
                .andExpect(jsonPath("$.userLastName", is("Lazur")))
                .andExpect(jsonPath("$.ibans", hasSize(1)))
                .andExpect(jsonPath("$.ibans[0].id", is(1)))
                .andExpect(jsonPath("$.ibans[0].bankName", is("bankName")))
                .andExpect(jsonPath("$.ibans[0].bankLocalisation", is("bankLocalisation")))
                .andExpect(jsonPath("$.ibans[0].countryCode", is("PL")))
                .andExpect(jsonPath("$.ibans[0].currencyCode", is("PLN")))
                .andExpect(jsonPath("$.ibans[0].ibanNumber", is("111111111111111111111111111111")))
                .andExpect(jsonPath("$.transactions", hasSize(1)))
                .andExpect(jsonPath("$.transactions[0].id", is(1)))
                .andExpect(jsonPath("$.transactions[0].uniqueKeyChain", is("aaaa")))
                .andExpect(jsonPath("$.transactions[0].userEmail", is("test@test.com")))
                .andExpect(jsonPath("$.transactions[0].inputIbanNumber", is("inputIbanNumber")))
                .andExpect(jsonPath("$.transactions[0].outputIbanNumber", is("outputIbanNumber")))
                .andExpect(jsonPath("$.transactions[0].pairOfCurrencies", is("PLN-EUR")))
                .andExpect(jsonPath("$.transactions[0].inputValue", is(1)))
                .andExpect(jsonPath("$.transactions[0].outputValue", is(1)))
                .andExpect(jsonPath("$.transactions[0].apiCurrencyPurchaseMultiplier", is(1)))
                .andExpect(jsonPath("$.transactions[0].currencySaleMultiplier", is(1)))
                .andExpect(jsonPath("$.transactions[0].profit", is(1)))
                .andExpect(jsonPath("$.transactions[0].date", is("2020-01-01")));
    }

    @Test
    public void shouldUnblockUser() throws Exception {
        //Given
        UserServiceDb userServiceDb = mock(UserServiceDb.class);
        doNothing().when(userServiceDb).unblockUser(userEmailDto);
        String jsonContent = new Gson().toJson(userEmailDto);
        //When & Then
        mockMvc.perform(post("/v1/user/unblockUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUserSet() throws Exception {
        //Given
        UserServiceDb userServiceDb = mock(UserServiceDb.class);
        doNothing().when(userServiceDb).deleteAllUsers();
        //When & Then
        mockMvc.perform(delete("/v1/user/deleteAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUserById() throws Exception {
        //Given
        Long userId = userDto.getId();
        UserServiceDb userServiceDb = mock(UserServiceDb.class);
        doNothing().when(userServiceDb).deleteUserById(userId);
        //When & Then
        mockMvc.perform(delete("/v1/user/deleteUser?userId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
