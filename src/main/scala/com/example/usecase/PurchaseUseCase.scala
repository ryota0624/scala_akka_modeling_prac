package com.example.usecase

import com.example.PurchaseService.Output
import com.example.{Member, MemberRepository, Product, ProductCount, ProductRepository, PurchaseService, Shop, ShopRepository, Staff, StaffRepository}

import scala.concurrent.{ExecutionContext, Future}

trait PurchaseUseCase {
  val purchaseService: PurchaseService

  val shopRepository: ShopRepository
  val staffRepository: StaffRepository
  val memberRepository: MemberRepository
  val productRepository: ProductRepository

  def exec(
          shopId: Shop.ID,
          staffId: Staff.ID,
          memberIdOpt: Option[Member.ID],
          products: Seq[(Product.Code, ProductCount)]
          )(implicit ctx: ExecutionContext) = {

    val productCountMapByCode = products.toMap
    val shopFOpt = shopRepository.findByID(shopId)
    val staffFOpt = staffRepository.findByID(staffId)
    val memberFOpt = memberIdOpt.map(memberRepository.findByID).getOrElse { sys.error("") }
    val productIds = products.map(_._1)
    val productsF = productRepository.findByIDs(productIds)
    val serviceOutputOptF = for {
      shopOpt <- shopFOpt
      staffOpt <- staffFOpt
      memberOpt <- memberFOpt
      products <- productsF
    } yield {
      val inputOpt = for {
        shop <- shopOpt
        staff <- staffOpt
      } yield {
        val productWithCountSeq = products.map {
          product => (product, productCountMapByCode(product.code))
        }
        PurchaseService.Input(
          shop = shop,
          staff = staff,
          member = memberOpt,
          products = productWithCountSeq
        )
      }
      inputOpt.map(purchaseService)
    }

    serviceOutputOptF.flatMap {
      _.fold[Future[Output]](Future.failed(???))(Future.successful)
    }

  }
}
