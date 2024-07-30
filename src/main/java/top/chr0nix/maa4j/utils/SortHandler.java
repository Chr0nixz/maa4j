package top.chr0nix.maa4j.utils;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SortHandler {

    public static Sort getSort(HashMap<String, Integer> hashMap) {
        ArrayList<Sort.Order> orders = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            switch (entry.getValue()) {
                case 0 -> orders.add(Sort.Order.asc(entry.getKey()));
                case 1 -> orders.add(Sort.Order.desc(entry.getKey()));
            }
        }
        return Sort.by(orders);
    }

}
