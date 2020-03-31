package com.xplore.server.entity

import com.xplore.database.entity.Record.ID
import com.xplore.server.entity.EntityEvent.EventKind

case class EntityEvent[T](id: ID, kind: EventKind, maybeEntity: Option[T])

object EntityEvent {

  sealed trait EventKind {
    val name: String
  }

  object EventKind {
    case object CREATED extends EventKind { override val name: String = "CREATED" }
    case object UPDATED extends EventKind { override val name: String = "UPDATED" }
    case object DELETED extends EventKind { override val name: String = "DELETED" }
  }
}
