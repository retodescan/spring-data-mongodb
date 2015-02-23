package org.hospital.core.repository;

import org.bson.types.ObjectId;
import org.hospital.core.domain.Hospital;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by thiago on 2/22/15.
 */
@RepositoryRestResource(collectionResourceRel = "hospital", path = "hospital")
public interface HospitalRepository extends MongoRepository<Hospital, ObjectId> {
    List<Hospital> findByLocationNear(Point point, Distance distance);
}
