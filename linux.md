# 系统环境 CentOS-6.5 
------------
### 0. 终端提示 
	# 表示是root权限 	[root@localhost /]#
	$ 表示普通用户权限	[zhiyuan6666@localhost /]$ 
	
### 1.目录介绍
	/	根目录
	~	/root
	etc	存放系统配置目录
	home 除了root以外的所有用户默认在home下新建一个以用户名为文件夹名称的文件夹
	usr	所有用户安装的软件都放入到这个文件夹中
	在/usr/local下新建tmp文件夹，所有压缩包都上传至/usr/local/tmp中
### 2.常用命令
	cd	进入文件夹
	ls	显示当前文件夹的内容
	ll	详细列表
	pwd	显示工作路径
	mkdir	新建文件夹
	vi/vim	编辑文本（文件不存在，会自动创建文件）
		需要按i字母键或insert按键才能进去编辑
		编辑完成后按esc按键进入编辑完成状态在输入下面的命令
		：wq 保存退出
		：q  不保存退出
		：q！强制退出
	touch	创建空文件
	cat	查看文件全部内容
	head [-n] 文件名 查看文件的前n行 默认前10行
	tail [-n] 文件名 查看文件的后n行 默认后10行
		tailf [-n] 文件名  动态显示文件后n行内容
	echo '内容' >> 文件名 向文件添加内容
	ifconfig 打印网卡信息（查看ip）
	reboot	重启
	shutdown	关机
	tar zxvf 文件名	解压文件
	cp 源文件 新文件路径	复制文件
		复制文件夹时  要带参数 -r
	mv 源文件 新文件地址 	剪切（可以重命名）
	rm	移除文件
		-f	默认删除(不会询问是否删除)
		-r	删除文件夹需要带参数-r
	clear	清屏
### 3.jdk配置
	3.1 将压缩包上传至/usr/local/tmp 文件夹中
	3.2 #tar zxvf 文件名
	3.3 解压后文件复制到/usr/local/jdk7 中
		#cp -r 文件名 /usr/local/jdk7
	3.4 配置环境变量
		vim /etc/profile
		修改文件内容
		export JAVA_HOME=/usr/local/jdk1.7
		export PATH=$JAVA_HOME/bin:$PATH
		export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
	3.5 解析文件或重启系统
		#source /etc/profile
	3.6 测试配置是否成功
		#java -version
	3.7 编写Demo.java
	3.8 #javac Demo.java 
	3.9 #java Demo
### 4.tomcat配置
	4.1 解压 
	4.2 复制文件夹到 /usr/local/tomcat
	4.3 配置环境变量 vi /etc/profile
		export TOMCAT_HOME=/usr/local/tomcat
		export CATALINA_HOME=/usr/local/tomcat
	4.4 放行端口
		# vim /etc/sysconfig/iptables
		-A INPUT -m state --state NEW -m tcp -p tcp --dport 8080 -j ACCEPT
		重启服务
		#service iptables restart
	4.5 启动tomcat
		#cd /usr/local/tomcat/bin
		#./start.sh & tailf ../logs/catalina.out
		通过浏览器访问tomcat欢迎页面
###### 	注意：这时候tomcat在重启的时候不会自动启动
###### 	https://blog.csdn.net/wangli61289/article/details/37924785
		1.修改/etc/rc.d/rc.local
			vi /etc/rc.d/rc.local 
		2.添加下面两行脚本，记住是两行，仅仅第二行不行，必须加第一行。在/etc/rc.d/rc.local文件最后加上：
			 export JAVA_HOME=/usr/java/jdk1.7.0_60
			/usr/local/tomcat/bin/startup.sh start
		说明：/usr/java/jdk1.7.0_60 是jdk安装目录
			/usr/local/tomcat是tomcat安装的目录
		 3.注意:修改rc.local文件为可执行
			chmod +x  rc.local

### 5.mysql配置
	5.1 解压 
	5.2 复制
		#cp -r 文件 ../tomcat
	5.3 新建用户组(为了不给mysql文件夹root权限)
		#groupadd mysql
	5.4 新建用户
		# useradd -r -g mysql mysql
	5.5 赋予权限
		#chown -R mysql:mysql ./
	5.6 初始化
		查看 /etc/my.cnf 是否存在如果存在就删除该文件
		#ls /etc/my.cnf
		#rm -f /etc/my.cnf
		初始化数据库
		#./scripts/mysql_install_db  --user=mysql
	5.7 修改配置文件
		cp support-files/my-default.cnf /etc/my.cnf
		cp support-files/mysql.server      /etc/rc.d/init.d/mysql
	5.8 重启mysql服务
			启动mysql服务：
			命令：service mysql start
			关闭服务：
			命令：service mysql stop
			重启服务：
			命令：service mysql restart

	5.9 操作数据库
		软连接
		#ln -s /usr/local/mysql/bin/mysql /usr/bin/mysql
		操作数据库
		#mysql -r root -p
###### 注意：这时候MySQL在重启的时候不会自动启动
###### 使用root用户在/etc/rc.d/rc.local 文件末尾加入如下内容
			#start mysql
			echo "Start MySQL..."
			service musql start;

### 	附1：忘记mysql怎么修改密码的方法
		进入/etc/my.cnf 在[mysql]下添加skip-grant-tables 启动安全模式
		命令：vi /etc/my.cnf
		重启服务：
		命令：service mysql restart
		登录mysql，输入密码时直接回车
		命令:  mysql -u root -p
		进入到mysql后，先使用mysql数据库
		命令：use mysql
		修改密码
		命令： update user set password= passworD ("smallming") where user='root';
		刷新权限
		命令： flush privileges;
		退出MySql编辑模式
		命令：exit
### 	附2：设置用户具有访问的权限
		进入mysql命令行
		# mysql -u root -p
		执行权限赋予命令
		#grant all privileges on *.* to 'root'@'%' identified by '密码' with grant option;
		刷新权限
		flush privileges;
		退出
		# quit