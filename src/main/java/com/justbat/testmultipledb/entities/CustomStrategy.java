package com.justbat.testmultipledb.entities;

import lombok.Data;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenizerDef;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = "customstrategy")
@Indexed(index = "emotomy_index6")
@AnalyzerDef(name = "strategy_analyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class))
public class CustomStrategy extends SearchableEmotomyItem {

    @EmbeddedId
    @FieldBridge(impl = CompositeIdBridge.class)
    private CustomStrategyPK customStrategyPK;

    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "strategy_analyzer"))
    @Column(insertable = false, updatable = false)
    private String symbol;
//
//
//    @Column(insertable = false, updatable = false)
//    private String loginName;


//    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "strategy_analyzer"))
//    private String name;
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "strategy_analyzer"))
    private String benchmark;

    @Column(name = "strategyName")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "strategy_analyzer"))
    private String strategyName;


    @Field(name = "advisorName", index = Index.YES, store = Store.YES, analyze = Analyze.YES)
    @FieldBridge(impl = SimpleMemberBridge.class)
    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loginName", insertable = false, updatable = false)
    private Member member;

    @Field
    @Transient
    private SearchableEmotomyItemType itemType = SearchableEmotomyItemType.CUSTOM_PORTFOLIO;
}