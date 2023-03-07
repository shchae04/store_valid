package start.intro.domain.member;

import lombok.Data;

@Data
public class Member {

    private Long id;

    private String loginId;

    private String userName;

    private String password;
}
