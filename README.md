# JLog
android 日志

项目基于https://github.com/ZhaoKaiQiang/KLog
重新整理了结构。新增了对java环境的支持

使用：
        JBuilder jBuilder = new JBuilder();
        jBuilder.setJLogLevelToFile(JLogLevelToFile.WARN);//设置打印到文件中的日志级别
        jBuilder.setTag("main");//设置全局的tag, 优先级别：指定tag>全局tag。如果两者都没有指定，则直接定位到打印语句所在的类
        jBuilder.setRuntimeType(RuntimeType.ANDROID);//设置在android环境下。有时候在android端使用java的mian方法，就需要切换成RuntimeType.JAVA环境
        jBuilder.setWriteToFile(true);//需要把日志写到log中
        jBuilder.setParentFile(Environment.getExternalStorageDirectory());//日志文件所在的文件夹
        jBuilder.setFileName("test.txt");//日志文件的名字
        JLog.setBuilder(jBuilder);

拓展：需要继承 BaseLog，重写2个方法。parseToString和getStackTraceIndex

    @Override
    public String parseToString(Object obj) {
        return obj.toString();
    }
    
一般情况下，这样就可以了。如果是自定义对象，可以自行处理。
    
    @Override
    public int getStackTraceIndex(String tag) {
        return tag == null ? 9 : 8;
    }
    
这里的下标需要重点说明一下。 用户可能没有设置tag的值，这时候需要自动获取代码所在类、方法和行数。
BaseLog 的wrapperContent  中 33行的代码的意思可以理解为，代码执行的树。它会记录每次执行的代码类、方法和行数。
从Thread.currentThread().getStackTrace() 对象中，取出StackTraceElement。
    
如何获取下标的值？ 那当然是debug,然后查看具体哪个下标下的取出StackTraceElement是你最外层的代码。记得判断tag==null。tag设和不设，所在的行数是不一样的。


记得在LogFactory中注册一下自定义的log

    dependencies {
        compile 'org.greenrobot:greendao:3.2.2' // add library
    }

    <dependency>
      <groupId>com.jisx.log</groupId>
      <artifactId>JLog</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
    </dependency>
