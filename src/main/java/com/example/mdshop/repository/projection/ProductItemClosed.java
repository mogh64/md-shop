package com.example.mdshop.repository.projection;

import java.math.BigDecimal;

public interface ProductItemClosed {
    BigDecimal getPrice();
    BigDecimal getDiscount();
}
