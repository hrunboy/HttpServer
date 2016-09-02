# HttpServer
A simple httpServer base on netty.

#功能
服务器启动后，通过调用特殊的URI（example: "/answer/?l=1,7,3,4"）访问服务器.
返回运算后的结果，解析：7\*3\*4,1\*3\*4,1\*7\*4,1\*7\*3

# 使用
1.在本地建立工程后，启动HttpServerReactor中的main方法
2.打开浏览器，输入 http://127.0.0.1:8080/answer/?l=1,7,3,4
3.返回应答信息：84,12,28,21

