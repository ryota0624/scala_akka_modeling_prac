package com

import scala.concurrent.{ExecutionContext, Future}

package object example {

  trait Repository[E, ID] {
    def findByID(id: ID)(implicit ctx: ExecutionContext): Future[Option[E]]
    def findByIDs(id: Seq[ID])(implicit ctx: ExecutionContext): Future[Seq[E]]
  }

}
