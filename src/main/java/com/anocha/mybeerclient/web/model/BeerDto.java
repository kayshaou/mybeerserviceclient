package com.anocha.mybeerclient.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BeerDto implements Serializable {

    private UUID id;
    private Integer version;
    private OffsetDateTime createdDatetime;
    private OffsetDateTime lastModified;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;
    private BigDecimal price;
    private Integer quantityOnHand;


}
