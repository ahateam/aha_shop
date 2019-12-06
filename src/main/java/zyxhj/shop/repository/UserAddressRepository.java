package zyxhj.shop.repository;

import zyxhj.shop.domain.UserAddress;
import zyxhj.utils.data.rds.RDSRepository;

public class UserAddressRepository extends RDSRepository<UserAddress> {

	public UserAddressRepository() {
		super(UserAddress.class);
	}

}
