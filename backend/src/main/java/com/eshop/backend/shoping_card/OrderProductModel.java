package com.eshop.backend.shoping_card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductModel {
    private Long id;

    private Long productId;

    private Long orderCardId;

    private Integer inCardProductAmount;

    private Integer inCardProductPrice;
}
