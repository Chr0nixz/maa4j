package top.chr0nix.maa4j.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSan {

    @Builder.Default
    int curSan = 0;

    @Builder.Default
    int maxSan = 135;

    public void addSan(int num) {
        curSan += num;
    }

}
