package cupet.com.demo.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    public int cupet_board_no;
    public String cupet_board_title;
    public String cupet_board_content;
    public int cupet_board_viewcnt;
    public String cupet_board_regdate;
    public int cupet_board_head_no;
    public String cupet_user_id;
    public String cupet_user_name;
}
//commit이 안된다
