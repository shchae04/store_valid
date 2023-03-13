package start.intro.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {


    @NotEmpty
    private String loginId;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;
    @NotEmpty
    private Long id;
}
