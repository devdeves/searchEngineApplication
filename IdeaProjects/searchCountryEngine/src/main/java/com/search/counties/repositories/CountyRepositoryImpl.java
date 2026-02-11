package com.search.counties.repositories;

import com.search.counties.model.County;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class CountyRepositoryImpl implements CountyRepository {

    private List<County> counties;

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream =
                getClass().getResourceAsStream("/counties.json");

        counties = mapper.readValue(inputStream,
                new TypeReference<List<County>>() {});
    }

    @Override
    public List<County> search(String query) {

        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }

        String[] parts = query.toLowerCase(Locale.ROOT).split(",");
        String namePart = parts[0].trim();
        String statePart = parts.length > 1 ? parts[1].trim() : null;

        return counties.stream()
                .filter(c -> {

                    boolean matchesName =
                            c.getName().toLowerCase(Locale.ROOT)
                                    .contains(namePart);

                    boolean matchesState = statePart == null ||
                            c.getState().toLowerCase(Locale.ROOT)
                                    .contains(statePart);

                    return matchesName && matchesState;
                })
                .limit(5)
                .collect(Collectors.toList());
    }
}
