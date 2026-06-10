package com.eoffice.service;

import com.eoffice.model.MachineDetails;
import com.eoffice.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;

    public List<MachineDetails> getByPressId(String pressId) {

        return machineRepository.findByPressId(pressId);
    }
}