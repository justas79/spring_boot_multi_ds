package com.justbat.testmultipledb.service;

import com.justbat.testmultipledb.entities.CustomProfile;
import com.justbat.testmultipledb.entities.CustomStrategy;
import com.justbat.testmultipledb.entities.SearchableEmotomyItem;
import com.justbat.testmultipledb.repository.bokbn.CustomStrategyRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class LuceneIndexServiceBean {

    private FullTextEntityManager fullTextEntityManager;
    private CustomStrategyRepo customStrategyRepo;

    public LuceneIndexServiceBean(EntityManagerFactory em) {
        this.fullTextEntityManager = Search.getFullTextEntityManager(em.createEntityManager());
    }

    public void triggerAll() {

        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            log.error("smth wrong indexing", e);
        }
    }

    public List<SearchableEmotomyItem> searchAll(String q) {

        List<SearchableEmotomyItem> results = new ArrayList<>();

        String[] fields = {"symbol", "name"};
        Map<String, Float> boostsPerField = new HashMap<String, Float>(2);
        boostsPerField.put("name", (float) 4);
        boostsPerField.put("symbol", (float) 1);
        QueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer(), boostsPerField);


        Query query = null;
        try {
            query = parser.parse(q + "*");

        } catch (ParseException e) {
            log.error("cannot parse search q {}", e);
            return results;
        }

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.fullTextEntityManager);
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query,
                CustomStrategy.class,
                CustomProfile.class);

        List resultList = fullTextQuery.getResultList();
        log.error("LUCENE RETRIEVED {} docs", resultList.size());
        results.addAll(resultList);
        return results;


    }

//    public List<ResultItemDto> searchItemsThrouhMultifieldParser(String text) {
//
//        String[] fields = {"title", "keywords"};
//        Map<String, Float> boostsPerField = new HashMap<String, Float>(2);
//        boostsPerField.put("title", (float) 4);
//        boostsPerField.put("keywords", (float) 1);
//        QueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer(), boostsPerField);
//
//        Query query = null;
//        try {
//            query = parser.parse(text);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (query == null) {
//            return new ArrayList<>();
//        }
//
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.fullTextEntityManager);
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Activity.class, Accommodation.class);
//        List resultList = fullTextQuery.getResultList();
//
//        List<ResultItemDto> results = new ArrayList<>();
//        resultList.forEach(i -> {
//            if (i instanceof Activity) {
//                Activity a = (Activity) i;
//                results.add(
//                        ResultItemDto.builder()
//                                .id(a.getId())
//                                .title(a.getTitle())
//                                .build());
//            } else if (i instanceof Accommodation) {
//                Accommodation a = (Accommodation) i;
//                results.add(
//                        ResultItemDto.builder()
//                                .id(a.getId())
//                                .title(a.getTitle())
//                                .keywords(a.getKeywords())
//                                .build());
//
//            }
//        });
//
//
//        return results;
//
//
//    }


}
