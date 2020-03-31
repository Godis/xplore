package com.xplore.server.akkahttp.entity.product

import akka.actor.ActorRef
import akka.stream.Materializer
import com.xplore.database.entity.product.ProductRepository
import com.xplore.database.entity.{EntityRepository, RecordRepository}
import com.xplore.database.mongo.entity.product.ProductRecord
import com.xplore.domain.Product
import com.xplore.server.akkahttp.entity.EntityRoutes
import com.xplore.server.akkahttp.entity.json.JsonSupport._
import com.xplore.server.entity.product.{ProductRequest, ProductRequestValidator, ProductResponse, ProductResponseFactory}
import com.xplore.server.entity.{RequestValidator, ResponseFactory, WebSocketNotifier}

import scala.concurrent.Future

class ProductRoutes(validator: RequestValidator[ProductRequest, Product],
                    repository: EntityRepository[Future, Product, ProductRecord],
                    factory: ResponseFactory[Product, ProductResponse],
                    webSocketActor: ActorRef)(implicit materializer: Materializer)
  extends EntityRoutes(rootPath = "products", validator, repository, factory, webSocketActor)

object ProductRoutes {

  def apply(repository: RecordRepository[Future, ProductRecord], webSocketActor: ActorRef)
           (implicit materializer: Materializer): ProductRoutes = {
    new ProductRoutes(ProductRequestValidator(), ProductRepository(repository, WebSocketNotifier(webSocketActor)), ProductResponseFactory(), webSocketActor)
  }
}