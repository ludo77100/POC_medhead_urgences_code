package com.medhead.patientsmicroservice.Repositories;

import com.medhead.patientsmicroservice.Entities.PostalAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalAddressRepository extends CrudRepository<Long, PostalAddress> {
}
