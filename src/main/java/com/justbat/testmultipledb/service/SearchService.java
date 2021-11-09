package com.justbat.testmultipledb.service;

import com.justbat.testmultipledb.entities.SearchableEmotomyItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SearchService {

    private final LuceneIndexServiceBean luceneIndexServiceBean;

    public List<SearchableEmotomyItem> getEmotomyItems(String q) {
        List<SearchableEmotomyItem> searchableEmotomyItems = luceneIndexServiceBean.searchAll(q);
        return searchableEmotomyItems;
    }


}
