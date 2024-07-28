package top.chr0nix.maa4j.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class SortDTO {

    //0:顺；1：逆
    int direction;

    String property;

}
