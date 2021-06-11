<span style="color: green">feign接口sk验证，排除直接使用http请求</span></br>
支持feign接口sk检查，避免接口直接使用http调用</br> 
#配置文件格式  

<span style="color: green">验证那些接口,支持通配符</span></br>
config.feign.patterns[0]=/api/**
config.feign.patterns[1]=/test/**

#
<span style="color: yellow">1.开启方式，引入依赖</span></br>
<dependency></br>
<groupId>io.github.haifeng303</groupId></br>
<artifactId>feng-cloud-feign-sign</artifactId></br>
</dependency></br>
<span style="color: yellow">2.使用注解打开配置</span></br>
@EnableFeignSecurity</br>



