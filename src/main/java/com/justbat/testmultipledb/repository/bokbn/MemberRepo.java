package com.justbat.testmultipledb.repository.bokbn;

import com.justbat.testmultipledb.entities.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Member, Integer> {
}
