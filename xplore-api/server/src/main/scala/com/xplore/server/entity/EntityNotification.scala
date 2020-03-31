package com.xplore.server.entity

import com.xplore.database.entity.Record.ID
import com.xplore.server.entity.EntityEvent.EventKind

case class EntityNotification[Res](id: ID, kind: EventKind, maybeResponse: Option[Res])

