package cupet.com.demo.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSelectoptionVO {
    public int cupet_board_head_no;
    public String cupet_board_head_name;
}
