package start.intro.web.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveItemForm {

    @NotNull
    private String itemName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;
}
