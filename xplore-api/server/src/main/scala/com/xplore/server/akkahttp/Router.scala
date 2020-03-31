package com.xplore.server.akkahttp

import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.xplore.database.entity.RecordRepository
import com.xplore.database.mongo.entity.product.ProductRecord
import com.xplore.server.akkahttp.entity.product.ProductRoutes
import com.xplore.server.akkahttp.web.WebRoutes

import scala.concurrent.Future

class Router(webRoutes: WebRoutes, productRoutes: ProductRoutes) extends CorsSupport {

  val routes: Route = webRoutes.route ~ corsHandler(productRoutes.route)
}

object Router {

  def apply(repository: RecordRepository[Future, ProductRecord], webSocketActor: ActorRef)(implicit materializer: Materializer): Router = {
    new Router(WebRoutes(), ProductRoutes(repository, webSocketActor))
  }
}
