package zyxhj.shop.repository;

import zyxhj.shop.domain.Store;
import zyxhj.utils.data.rds.RDSRepository;

public class StoreRepository extends RDSRepository<Store>{

	public  StoreRepository() {
		super(Store.class);
	}

}
