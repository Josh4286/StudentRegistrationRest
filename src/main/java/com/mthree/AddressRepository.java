package com.mthree;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	List<Address> findListAddressByStreet(String street);
	Optional<Address> findAddressByStreet(String street);
}
