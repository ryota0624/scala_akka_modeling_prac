package com.example

import java.util.Date

class Price(val value: Int) extends AnyVal

class ProductPrice(
                    val productCode: Product.Code,
                    val price: Price,
                    val appliedStartAt: PriceAppliedStartAt,
                    val appliedEndAt: PriceAppliedEndAt
                  )

class PriceAppliedStartAt(private val date: Date)

class PriceAppliedEndAt(private val date: Date)
