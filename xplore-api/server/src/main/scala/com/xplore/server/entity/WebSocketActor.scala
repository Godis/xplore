package com.xplore.server.entity

import akka.actor.{Actor, ActorRef, Props}
import com.xplore.server.entity.WebSocketActor.Message.{OnConnection, OnEntityEvent}

import scala.collection.mutable

class WebSocketActor extends Actor {

  private val connections = mutable.Buffer.empty[ActorRef]

  override def receive: Receive = {
    case OnConnection(actorRef) =>
      connections += actorRef

    case OnEntityEvent(event) =>
      connections.foreach { connection =>
        connection ! event
      }
  }
}

object WebSocketActor {

  def props: Props = Props(classOf[WebSocketActor])

  sealed trait Message
  object Message {
    case class OnConnection(actorRef: ActorRef) extends Message
    case class OnEntityEvent[T](event: EntityEvent[T]) extends Message
  }
}
