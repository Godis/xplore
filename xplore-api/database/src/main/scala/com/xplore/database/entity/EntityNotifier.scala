package com.xplore.database.entity

import com.xplore.database.entity.Record.ID

trait EntityNotifier[E] {

  def notifyCreated(id: ID, entity: E): Unit
  def notifyUpdated(id: ID, entity: E): Unit
  def notifyDeleted(id: ID): Unit
}
