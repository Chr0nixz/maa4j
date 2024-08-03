package top.chr0nix.maa4j.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class GetPageDTO {

    int pageNum;

    int size;

    HashMap<String, Integer> orders;

}
