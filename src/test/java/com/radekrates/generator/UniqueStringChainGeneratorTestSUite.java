package com.radekrates.generator;

import com.radekrates.service.generators.UniqueStringChainGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniqueStringChainGeneratorTestSUite {
    @Autowired
    private UniqueStringChainGenerator generator;

    @Test
    public void testUniqueStringGenerator() {
        //Given
        //When
        String generatedString = generator.createUniqueStringChain();
        //Then
        assertEquals(50, generatedString.length());
    }
}
