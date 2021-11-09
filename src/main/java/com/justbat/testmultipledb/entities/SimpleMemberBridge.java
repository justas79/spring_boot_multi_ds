package com.justbat.testmultipledb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.bridge.TwoWayStringBridge;

@NoArgsConstructor
@Data
public class SimpleMemberBridge implements TwoWayStringBridge {

    @Override
    public String objectToString(Object object) {
        Member pk = (Member) object;
        return pk.getAdvisor();
    }

    @Override
    public Object stringToObject(String stringValue) {
        Member m = new Member();
        m.setAdvisor(stringValue);
        return m;
    }
}
