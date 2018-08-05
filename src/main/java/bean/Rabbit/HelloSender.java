package bean.Rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * 这边相当与是业务场景使用rabbitTemplate工厂类生产出来对应的amqp
 * 来对MQ中对应的  消息队列加入对应列表中。
 * 这里的  hello   就相当于是MAP中的key    而context是value
 * 所以说 这个就象是map中有个队列  根据对应的key  找到队列在从队列中获取对应的数据
 */
@Controller
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String string) {
//        String context = "hello " + new Date();
        System.out.println("Sender : " + string);
        this.rabbitTemplate.convertAndSend("hello", string);
    }
}
