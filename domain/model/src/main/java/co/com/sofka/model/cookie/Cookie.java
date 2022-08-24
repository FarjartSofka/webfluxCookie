package co.com.sofka.model.cookie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cookie {

    private Integer code;
    private String name;

}
