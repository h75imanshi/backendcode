package com.eoffice.service;

import com.eoffice.dto.NewspaperDto;
import com.eoffice.repository.NewspaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewspaperService {

    private final NewspaperRepository newspaperRepository;

    public List<NewspaperDto> getByPressName(String pressName) {

        return newspaperRepository.findByPressName(pressName);

    }

}