package com.aronith.vet.service;

import com.aronith.vet.repository.RazaRepository;
import com.aronith.vet.service.impl.RazaServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RazaServiceTest {

    @Mock
    RazaRepository razaRepository;

    @InjectMocks
    RazaServiceImpl razaService;

}
