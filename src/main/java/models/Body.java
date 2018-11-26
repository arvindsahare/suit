package models;

import lombok.Data;

import java.util.List;
@Data
public class Body {
    String mode;
    List<BodyParams> urlencoded;
}
