package com.example

import java.util.Date

class Member(
            val id: Member.ID,
            val totalPoint: Point
            ) {
}

object Member {
  class ID(val value: String) extends AnyVal
}


class Point(val value: Int) extends AnyVal

class PurchasedAt(private val date: Date)

class Purchase(
              val id: Purchase.ID,
              val memberID: Member.ID,
              val shopId: Shop.ID,
              val grantedPoint: Point,
              val purchasedAt: PurchasedAt
              )

object Purchase {
  class ID(val value: String) extends AnyVal
}

trait MemberRepository extends Repository[Member, Member.ID] {}

trait PurchaseRepository extends Repository[Purchase, Purchase.ID] {}

/*
[Member] 1 - * [Purchase]
 */
