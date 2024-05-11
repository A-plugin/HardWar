package org.apo.hardwar.System;

import java.util.HashMap;
import java.util.Map;

public class ItemNameTranslator {
    private static final Map<String, String> nameMap = new HashMap<>();

    static {
        nameMap.put("DIAMOND", "다이아몬드");
        nameMap.put("NETHERITE", "네더라이트");
        nameMap.put("IRON", "철");
        nameMap.put("GOLD", "금");
        nameMap.put("STONE", "돌");
        nameMap.put("WOODEN", "나무");
        nameMap.put("CHAINMAIL", "사슬");

        nameMap.put("SWORD", "검");
        nameMap.put("AXE", "도끼");
        nameMap.put("SHOVEL", "삽");
        nameMap.put("PICKAXE", "곡괭이");
        nameMap.put("HOE", "괭이");

        nameMap.put("HELMET", "투구");
        nameMap.put("CHESTPLATE", "흉갑");
        nameMap.put("LEGGINGS", "레깅스");
        nameMap.put("BOOTS", "부츠");

        nameMap.put("TRIDENT", "삼지창");
        nameMap.put("SHIELD", "방패");
    }

    public String translate(String n) {
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            n = n.replace(entry.getKey(), entry.getValue());
        }
        return n.replace("_", " ");
    }
}
