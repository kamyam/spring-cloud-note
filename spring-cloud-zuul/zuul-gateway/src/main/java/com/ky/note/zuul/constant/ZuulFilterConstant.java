package com.ky.note.zuul.constant;

/**
 * PRE: PRE过滤器用于将请求路径与配置的路由规则进行匹配，以找到需要转发的目标地址，并做一些前置加工，比如请求的校验等；
 * ROUTING: ROUTING过滤器用于将外部请求转发到具体服务实例上去；
 * POST: POST过滤器用于将微服务的响应信息返回到客户端，这个过程种可以对返回数据进行加工处理；
 * ERROR: 上述的过程发生异常后将调用ERROR过滤器。ERROR过滤器捕获到异常后需要将异常信息返回给客户端，所以最终还是会调用POST过滤器。
 */
public interface ZuulFilterConstant {
    String PRE = "pre";
    String ROUTING = "routing";
    String POST = "post";
    String ERROR = "error";
}
