package com.eoffice.service;

import com.eoffice.model.KeeperDetails;
import com.eoffice.repository.KeeperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeeperService {

    private final KeeperRepository keeperRepository;

    public List<KeeperDetails> getByPressId(String pressId) {

        return keeperRepository.findByPressId(pressId);
    }
}