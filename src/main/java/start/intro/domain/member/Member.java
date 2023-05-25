package start.intro.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;




}
