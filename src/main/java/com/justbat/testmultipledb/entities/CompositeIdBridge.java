package com.justbat.testmultipledb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.bridge.TwoWayStringBridge;

@NoArgsConstructor
@Data
public class CompositeIdBridge implements TwoWayStringBridge {

    @Override
    public String objectToString(Object object) {
        CustomStrategyPK pk = (CustomStrategyPK) object;
        return pk.getSymbol() + ";" + pk.getLoginName();
    }

    @Override
    public Object stringToObject(String stringValue) {
        String[] components = stringValue.split(";");
        CustomStrategyPK pk = new CustomStrategyPK();
        pk.setSymbol(components[0]);
        pk.setLoginName(components[1]);
        return pk;
    }
}
