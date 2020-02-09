package com.radekrates.controller.admincontroller;

import com.radekrates.service.transactionfactory.TransactionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    @Autowired
    private TransactionFactory transactionFactory;

    @GetMapping(value = "setRatioInDecimal")
    @ResponseStatus(value = HttpStatus.OK, reason = "Server acceptance")
    public void setRatioInDecimal(@RequestParam BigDecimal ratioInDecimal) {
        transactionFactory.setRatio(ratioInDecimal);
    }
}
