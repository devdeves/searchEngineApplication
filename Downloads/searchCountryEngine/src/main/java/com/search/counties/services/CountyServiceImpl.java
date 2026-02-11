package com.search.counties.services;

import com.search.counties.dto.CountyResponseDto;
import com.search.counties.model.County;
import com.search.counties.repositories.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountyServiceImpl implements CountyService {

    private final CountyRepository repository;

    public CountyServiceImpl(CountyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CountyResponseDto> suggest(String query) {

        List<County> counties = repository.search(query);

        return counties.stream()
                .map(c -> new CountyResponseDto(
                        c.getFips(),
                        c.getState(),
                        c.getName()
                ))
                .collect(Collectors.toList());
    }
}
