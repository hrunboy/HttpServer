package ron.server.httpServer;

public class HttpServerReactor {

	public static void main(String[] args) {
		HttpServer httpServer = HttpServer.open();
		httpServer.conifg("127.0.0.1", 8080);
		httpServer.start();
		System.out.println("服务器启动");

	}

}
