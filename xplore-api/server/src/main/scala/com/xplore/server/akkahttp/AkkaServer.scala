package com.xplore.server.akkahttp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.xplore.database.entity.RecordRepository
import com.xplore.database.mongo.entity.product.ProductRecord
import com.xplore.server.Server
import com.xplore.server.Server.Handle
import com.xplore.server.entity.WebSocketActor
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class AkkaServer(config: AkkaConfig, router: Router)
                (implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContext) extends Server[Future] {

  val log: Logger = LoggerFactory.getLogger(getClass)

  override def run(): Future[Server.Handle[Future]] = {
    log.info("Server starting..")

    Http()
      .bindAndHandle(router.routes, config.host, config.port)
      .map { binding ⇒
        Handle[Future] {
          for {
            _ <- binding.terminate(5.seconds)
            _ ← system.terminate()
          } yield log.info("Server terminated..")
        }
      }
      .andThen {
        case Success(_) ⇒
          log.info("Server started..")
        case Failure(ex) ⇒
          log.warn("Server startup failed..", ex)
      }
  }
}

object AkkaServer {

  def apply(config: AkkaConfig, repository: RecordRepository[Future, ProductRecord])
           (implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContext): AkkaServer = {

    val webSocketActor = system.actorOf(WebSocketActor.props)
    new AkkaServer(config, Router(repository, webSocketActor))
  }
}