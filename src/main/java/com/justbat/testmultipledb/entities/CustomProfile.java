package com.justbat.testmultipledb.entities;

import lombok.Data;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenizerDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Data
//@Indexed(index = "emotomy_index5")
@Table(name = "customprofiles")
@AnalyzerDef(name = "customprofile_analyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class))
public class CustomProfile extends SearchableEmotomyItem {


    @Id
    @DocumentId
    private Integer id;

    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customprofile_analyzer"))
    private String symbol;
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customprofile_analyzer"))
    private String name;
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customprofile_analyzer"))
    @Column(name = "printName")
    private String printName;
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES)
    @Column(name = "advisorName")
    private String advisorName;
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES)
    private String benchmark;

    @Field
    @Transient
    private SearchableEmotomyItemType itemType = SearchableEmotomyItemType.BUILDING_BLOCK;
}