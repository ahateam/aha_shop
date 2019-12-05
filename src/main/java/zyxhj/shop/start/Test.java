package zyxhj.shop.start;

import com.alibaba.druid.pool.DruidDataSource;
import com.alicloud.openservices.tablestore.SyncClient;

import zyxhj.shop.domain.Channel;
import zyxhj.shop.domain.Order;
import zyxhj.shop.domain.Product;
import zyxhj.shop.domain.ProductSkuInfo;
import zyxhj.shop.domain.RecommendProduct;
import zyxhj.shop.domain.Store;
import zyxhj.shop.domain.StoreProductTag;
import zyxhj.shop.domain.UserAddress;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.rds.RDSUtils;

public class Test {

	public static void main(String[] args) {

		testDB();

	}

	private static void testDB() {
		System.out.println("testDB");

		try {
			DruidDataSource dds = DataSource.getDruidDataSource("rdsDefault.prop");
			SyncClient client = DataSource.getTableStoreSyncClient("tsDefault.prop");

			RDSUtils.createTableByEntity(dds, Channel.class);
			RDSUtils.createTableByEntity(dds, Order.class);
			RDSUtils.createTableByEntity(dds, Product.class);
			RDSUtils.createTableByEntity(dds, ProductSkuInfo.class);
			RDSUtils.createTableByEntity(dds, RecommendProduct.class);
			RDSUtils.createTableByEntity(dds, Store.class);
			RDSUtils.createTableByEntity(dds, StoreProductTag.class);
			RDSUtils.createTableByEntity(dds, UserAddress.class);

//			TSUtils.createTableByEntity(client, Appraise.class);

//			TSUtils.drapTableByEntity(client, Reply.class);
			client.shutdown();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
