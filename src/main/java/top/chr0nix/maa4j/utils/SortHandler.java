package top.chr0nix.maa4j.utils;

import org.springframework.data.domain.Sort;
import top.chr0nix.maa4j.dto.SortDTO;

public class SortHandler {

    public static Sort getSort(SortDTO sortDTO) {
        return switch (sortDTO.getDirection()) {
            case 0 -> Sort.by(Sort.Order.asc(sortDTO.getProperty()));
            case 1 -> Sort.by(Sort.Order.desc(sortDTO.getProperty()));
            default -> Sort.by(Sort.Order.asc("ID"));
        };
    }

}
