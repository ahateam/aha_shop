package zyxhj.shop.repository;

import zyxhj.shop.domain.ProductSkuInfo;
import zyxhj.utils.data.rds.RDSRepository;

public class ProductSkuRepository  extends RDSRepository<ProductSkuInfo>{

	public ProductSkuRepository() {
		super(ProductSkuInfo.class);
	}

	
}
