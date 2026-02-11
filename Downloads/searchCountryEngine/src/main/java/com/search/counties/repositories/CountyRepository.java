package com.search.counties.repositories;

import com.search.counties.model.County;
import java.util.List;

public interface CountyRepository {

    List<County> search(String query);
}
