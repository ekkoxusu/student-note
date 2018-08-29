# ubantu安装环境

[TOC]

## JDK

[下载JDK](http://www.oracle.com/technetwork/java/javase/overview/index.html)

解压 配置环境变量

```linux
vi ~/.bashrc_profile 
export JAVA_HOME=/var/local/jdk1.8.0_171
export PATH=/bin:/usr/local/sbin:/usr/local/bin:/sbin:/usr/sbin:/usr/bin:$JAVA_HOME/bin
source ~/.bashrc_profile
```

## MYSQL

[下载mysql](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)

>
>   pwd /usr/local/mysql

安装

> ./scripts/mysql_install_db
>
> ** 此处注意事项 **
>
> 必须在mysql根目录去执行,因为 mysql_install_db使用相对路径

启动服务

>  sudo ./support-files/mysql.server start 

如果是其他服务器拉过来的代码

> rm -r ./data

设置数据库外部链接权限

> \*.\* 代表 所有database的所有表 root表示用户名 %表示支持外部链接的ip
>
> grant all PRIVILEGES on \*.\* to root@'%'  identified by 'password';

## NGINX

[下载NGINX](http://nginx.org/en/download.html)

解压缩

修改配置

#### 配置解释

``` nginx
worker_processes  1; //nginx核心数 ->一般为CPU核数 查看使用lscpu
events {
    worker_connections  1024; // 一般为 worker_processes*worker_connections/4 
}
http {
    /**
    * 下面2个是指默认为这些属性,防止css加载为text/plain
    **/
    include       mime.types; 
    default_type  application/octet-stream;
    client_header_buffer_size 2MB; #设置上传文件大小限制
    large_client_header_buffers 1MB; # 请求缓存
    client_max_body_size 8m;   #请求body缓存
    sendfile on; #高效传输模式 轻磁盘IO应用可开启
    autoindex on # 目录列表访问(ftp的感觉)
    tcp_nopush on;#防止网络阻塞
    tcp_nodelay on; #防止网络阻塞
	keepalive_timeout 120; #长连接超时时间，单位是秒
    upstream su.student.com {
	#upstream的负载均衡，weight是权重，可以根据机器配置定义权重。weigth参数表示权值，权值越高被分配到的几率越大。
	server 192.168.80.121:80 weight=3;
	server 192.168.80.122:80 weight=2;
	server 192.168.80.123:80 weight=3;
	}
    #虚拟主机的配置
    server{
        #监听端口
        listen 80;
        #域名可以有多个，用空格隔开
        server_name www.ha97.com ha97.com;
        index index.html index.htm index.php;
        root /data/www/ha97;
        location /{
            root   html;
            index  index.html index.htm;
            proxy_pass su.student.com; #反向代理
            server_name_in_redirect off; #分布式必开,防止内部域名出现外部
            #以下是一些反向代理的配置，可选。
            proxy_set_header Host $host;
            client_max_body_size 10m; #允许客户端请求的最大单文件字节数
            client_body_buffer_size 128k; #缓冲区代理缓冲用户端请求的最大字节数，
            proxy_connect_timeout 90; #nginx跟后端服务器连接超时时间(代理连接超时)
            proxy_send_timeout 90; #后端服务器数据回传时间(代理发送超时)
            proxy_read_timeout 90; #连接成功后，后端服务器响应时间(代理接收超时)
            proxy_buffer_size 4k; #设置代理服务器（nginx）保存用户头信息的缓冲区大小
            proxy_buffers 4 32k; #proxy_buffers缓冲区，网页平均在32k以下的设置
            proxy_busy_buffers_size 64k; #高负荷下缓冲大小（proxy_buffers*2）
            proxy_temp_file_write_size 64k;
        }
    	#图片缓存时间设置
    	location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)${
    		expires 10d;
    	}
    	#JS和CSS缓存时间设置
    	location ~ .*\.(js|css)?${
            expires 1h;
    	}
}
```

启动

> /usr/local/nginx/sbin/nginx start

停止

> /usr/local/nginx/sbin/nginx stop 
>
> kill -9 'cat /usr/local/nginx/logs/nginx.pid'



## 本次常用linux命令

```linux
tar xcvf $source $target

cd

mv

cp $source $target

systemctl daemon-reload  #重新载入 systemd，扫描新的或有变动的单元
```



