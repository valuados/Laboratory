package com.netcracker.backend.service.impl;

import com.netcracker.backend.service.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("pseudoRandomGenerator")
public class IdGeneratorImpl implements IdGenerator {
    @Override
    public Integer generate() {
        return (new Random()).nextInt(Integer.MAX_VALUE);
    }
}
