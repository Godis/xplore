package com.xplore.database.mongo.entity.product

import com.xplore.database.entity.RecordFactory
import com.xplore.database.mongo.identifier.ObjectIdGenerator
import com.xplore.domain.identifier.IdGenerator
import com.xplore.domain.Product
import org.bson.types.ObjectId

class ProductRecordFactory(generator: IdGenerator[ObjectId]) extends RecordFactory[Product, ProductRecord] {

  override def create(entity: Product): ProductRecord = ProductRecord(
    generator.newId,
    entity.id,
    entity.category.underlying,
    entity.brand.underlying,
    entity.colour.underlying,
    entity.size.region.underlying,
    entity.size.value,
    entity.description
  )
}

object ProductRecordFactory {

  def apply(): ProductRecordFactory = new ProductRecordFactory(ObjectIdGenerator())
}
