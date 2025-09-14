package ru.yandex.practicum.onlinestore.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    private Long id;

    private String title;

    private String description;

    private String imagePath;

    private Integer count;

    private BigDecimal price;
}