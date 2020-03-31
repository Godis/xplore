package com.xplore.server.entity

import akka.actor.ActorRef
import com.xplore.database.entity.EntityNotifier
import com.xplore.database.entity.Record.ID
import com.xplore.server.entity.EntityEvent.EventKind.{CREATED, DELETED, UPDATED}
import com.xplore.server.entity.WebSocketActor.Message.OnEntityEvent

class WebSocketNotifier[T](webSocketActor: ActorRef) extends EntityNotifier[T] {

  override def notifyCreated(id: ID, entity: T): Unit = webSocketActor ! OnEntityEvent(EntityEvent(id, CREATED, Some(entity)))
  override def notifyUpdated(id: ID, entity: T): Unit = webSocketActor ! OnEntityEvent(EntityEvent(id, UPDATED, Some(entity)))
  override def notifyDeleted(id: ID): Unit = webSocketActor ! OnEntityEvent(EntityEvent(id, DELETED, Option.empty[T]))
}

object WebSocketNotifier {

  def apply[T](webSocketActor: ActorRef): WebSocketNotifier[T] = new WebSocketNotifier[T](webSocketActor)
}
