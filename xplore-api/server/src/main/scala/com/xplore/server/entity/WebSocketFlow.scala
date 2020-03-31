package com.xplore.server.entity

import akka.actor.ActorRef
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import akka.stream.{Materializer, OverflowStrategy}
import com.xplore.server.akkahttp.entity.json.JsonSupport._
import com.xplore.server.entity.WebSocketActor.Message.OnConnection
import spray.json._

object WebSocketFlow {

  def apply[E, Res: JsonFormat](webSocketActor: ActorRef, factory: ResponseFactory[E, Res])
                                  (implicit materializer: Materializer): Flow[Message, Message, Any] = {

    val (actorRef, publisher) = Source
      .actorRef[EntityEvent[E]](0, OverflowStrategy.fail)
      .map { case EntityEvent(id, kind, maybeEntity) => EntityNotification(id, kind, maybeEntity.map(factory.create)) }
      .map { notification: EntityNotification[Res] => TextMessage.Strict(notification.toJson.compactPrint) }
      .toMat(Sink.asPublisher(fanout = false))(Keep.both)
      .run()

    webSocketActor ! OnConnection(actorRef)

    val flow = Flow.fromSinkAndSource(Sink.ignore, Source.fromPublisher(publisher))

    flow
  }
}
