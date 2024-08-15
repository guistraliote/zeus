package net.guistraliote.zeus.brand;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class BrandDTO implements Serializable {
    private String name;
    private String description;
}
