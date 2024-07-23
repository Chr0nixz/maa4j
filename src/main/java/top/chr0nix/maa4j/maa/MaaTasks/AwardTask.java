package top.chr0nix.maa4j.maa.MaaTasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardTask extends MaaTask{
    
    boolean award;
    
    boolean mail;
    
    boolean recruit;
    
    boolean orundum;
    
    boolean mining;
    
}
