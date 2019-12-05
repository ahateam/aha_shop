package zyxhj.shop.start;


import io.vertx.core.Vertx;
import zyxhj.shop.service.UserService;
import zyxhj.utils.Singleton;
import zyxhj.utils.ZeroVerticle;

public class AHA_ShopVerticle extends ZeroVerticle {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new AHA_ShopVerticle());
	}

	public String name() {
		return "aha_shop";
	}

	public int port() {
		return 8081;
	}

	protected void init() throws Exception {

		initCtrl(ctrlMap, Singleton.ins(UserService.class, "user"));

	}
}
