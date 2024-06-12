package cupet.com.demo.board.selectoption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectoptionSearchVO {
    public int cupet_search_code;
    public String cupet_search_name;
}
