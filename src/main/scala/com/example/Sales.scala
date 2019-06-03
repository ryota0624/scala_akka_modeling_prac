package com.example

import java.util.Date

class Sales(
           val id: Sales.ID,
           val shopId: Shop.ID,
           val details: SalesDetails,
           val responsibleStaff: Staff.ID,
           val memberIdOpt: Option[Member.ID],
           val occurredAt: OccurredAt
           )

object Sales {
  class ID(val value: String) extends AnyVal
}


case class SalesDetails(private val value: Seq[SalesDetail])

class SalesDetail(
                 productCode: Product.Code,
                 count: ProductCount
                 )

object SalesDetail {
  def apply(tuple:
           (Product,
             ProductCount)
           ): SalesDetail = new SalesDetail(tuple._1.code, tuple._2)
}

class ProductCount(val value: Int) extends AnyVal

class AccountingDate(private val  date: Date)


class OccurredAt(private val date: Date)

object OccurredAt {
  def now = new OccurredAt(new Date)
}

trait SalesRepository extends Repository[Sales , Staff.ID] {}
