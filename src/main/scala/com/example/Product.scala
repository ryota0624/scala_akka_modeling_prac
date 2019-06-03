package com.example

class Maker(id: Maker.ID)

object Maker {

  class ID(val value: String) extends AnyVal
}


class Product(
               val code: Product.Code,
               val name: Product.Name,
               val category: ProductCategory
             )

object Product {

  class Code(val value: String) extends AnyVal

  class Name(val value: String) extends AnyVal

}


case class ProductCategory(name: ProductCategory.Name, properties: ProductProperty)

object ProductCategory {

  class Name(val value: String) extends AnyVal

}

case class ProductCategoryProperty(name: ProductCategoryProperty.Name, category: ProductCategory)

object ProductCategoryProperty {

  class Name(val value: String) extends AnyVal

}

case class ProductProperty(value: ProductProperty.Value, productCategoryProperties: Seq[ProductCategoryProperty])

object ProductProperty {

  class Value(val value: String) extends AnyVal
}

/*
Maker 1 - * Product
Product 1 -> * ProductProperty
Product 1 -> * ProductCategory
ProductCategory 1 -> * ProductCategoryProperty
 */

trait ProductRepository extends Repository[Product , Product.Code] {}
