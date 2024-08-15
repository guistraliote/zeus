package net.guistraliote.zeus.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class CategoryDTO {
    private Long id;
    private String name;
    private Long parentId;
    private String level;
    private String path;
}
