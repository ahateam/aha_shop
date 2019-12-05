package zyxhj.shop.repository;

import zyxhj.shop.domain.RecommendProduct;
import zyxhj.utils.data.rds.RDSRepository;

public class RecoProductRepository extends RDSRepository<RecommendProduct>{

	public  RecoProductRepository() {
		super(RecommendProduct.class);
	}

}
