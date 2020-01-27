package com.radekrates.service.operation;

import com.radekrates.api.datafixerio.client.DataFixerClient;
import com.radekrates.service.TransactionServiceDb;
import com.radekrates.service.UserServiceDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/transactionService")
public class TransactionBasedOnUserAndDataFixer {
    @Autowired
    private DataFixerClient dataFixerClient;
    @Autowired
    private TransactionServiceDb transactionServiceDb;
    @Autowired
    private UserServiceDb userServiceDb;

    public void createTransaction(@RequestParam Long userId) {

    }
}
