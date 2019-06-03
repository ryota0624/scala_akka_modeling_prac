package com.example

class Staff(val id: Staff.ID, val name: Staff.Name, val shopId: Shop.ID) {

}

object Staff {

  class ID(val value: String) extends AnyVal

  class Name(val value: String) extends AnyVal

}


class Shop(val id: Shop.ID)

object Shop {
  class ID(val value: String) extends AnyVal
}

trait ShopRepository extends Repository[Shop, Shop.ID] {}

trait StaffRepository extends Repository[Staff, Staff.ID] {}
