package com.search.counties.services;

import com.search.counties.dto.CountyResponseDto;
import java.util.List;

public interface CountyService {

    List<CountyResponseDto> suggest(String query);
}