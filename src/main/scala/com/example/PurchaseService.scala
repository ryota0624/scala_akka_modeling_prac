package com.example

import PurchaseService._

class PurchaseService extends (Input => Output) {
  override def apply(input: Input): Output = {
    val details = SalesDetails(input.products.map(SalesDetail.apply))
    val sales = new Sales(
      id = ???,
      shopId = input.shop.id,
      details = details,
      responsibleStaff = input.staff.id,
      memberIdOpt = input.member.map(_.id),
      occurredAt = OccurredAt.now
    )

    Output(sales = sales)
  }
}

object PurchaseService {
  case class Input(
                    shop: Shop,
                    staff: Staff,
                    member: Option[Member],
                    products: Seq[(Product, ProductCount)],
                  )

  case class Output(
                     sales: Sales
                   )
}
