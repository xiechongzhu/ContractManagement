package com.cy.contractmanagement.Utiliy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JqGridResultBuilder {
    public static Map<String, Object> builde(int rowNum, int page, long total, List collection) {
        Map map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("total", total % rowNum == 0 ? total / rowNum : total / rowNum + 1);
        map.put("records", total);
        map.put("rows", collection);
        return map;
    }
}
