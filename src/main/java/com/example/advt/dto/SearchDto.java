package com.example.advt.dto;

import com.example.advt.domain.City;
import com.example.advt.domain.Subcategory;
import lombok.Data;

import java.util.Objects;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *28.10.2019
 */
@Data
public class SearchDto {
    private City city;
    private Subcategory subcategory;
  private int found;
  private String urlSearch;
  private int categorySearch;

    public SearchDto(City city, Subcategory subcategory, int found, String urlSearch, int categorySearch) {
        this.city = city;
        this.subcategory = subcategory;
        this.found = found;
        this.urlSearch = urlSearch;
        this.categorySearch = categorySearch;
    }

    public SearchDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchDto)) return false;
        SearchDto searchDto = (SearchDto) o;
        return found == searchDto.found &&
                categorySearch == searchDto.categorySearch &&
                Objects.equals(city, searchDto.city) &&
                Objects.equals(subcategory, searchDto.subcategory) &&
                Objects.equals(urlSearch, searchDto.urlSearch);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, subcategory, found, urlSearch, categorySearch);
    }
}
