package com.justbat.testmultipledb.controller;

import com.justbat.testmultipledb.dto.ResultDto;
import com.justbat.testmultipledb.entities.CustomStrategy;
import com.justbat.testmultipledb.entities.Member;
import com.justbat.testmultipledb.entities.SearchableEmotomyItem;
import com.justbat.testmultipledb.repository.bokbn.CustomStrategyRepo;
import com.justbat.testmultipledb.repository.bokbn.MemberRepo;
import com.justbat.testmultipledb.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final SearchService searchService;
    private final CustomStrategyRepo customStrategyRepo;
    private final MemberRepo memberRepo;

    @GetMapping(value = "test")
    public ResponseEntity<List<SearchableEmotomyItem>> test(@RequestParam("q") String q) {
        List<SearchableEmotomyItem> items = searchService.getEmotomyItems(q);
        return ok(items);
    }

    @GetMapping(value = "testdb")
    public ResponseEntity<List<ResultDto>> testDb() {
        List<CustomStrategy> list = new ArrayList<>();
        customStrategyRepo.findAll().forEach(list::add);

        List<ResultDto> collect = list.stream().map(
                i -> ResultDto
                        .builder()
                        .symbol(i.getStrategyName())
                        .advisor(i.getMember().getLoginName())
                        .build())
                .collect(toList());

        return ok(collect);
    }

    @GetMapping(value = "testmember")
    public ResponseEntity<List<Member>> testMember() {
        List<Member> list = new ArrayList<>();
        memberRepo.findAll().forEach(list::add);
        return ok(list);
    }

}
