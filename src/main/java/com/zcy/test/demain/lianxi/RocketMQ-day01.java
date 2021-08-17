//package com.zcy.test.demain.lianxi;
////1.RocketMQ
////	概述: 用来收发消息(指令)
////	流程:
////		生产消息-"生产者"把消息发送给"命名服务器"
////		消费消息-"命名服务器"把消息交给"消费者"
////
////2.RocketMQ-生产者
//	public class Producer {
//		public static void main(String[] args) throws Exception {
//			//1.创建一个发送消息的对象Producer
//			DefaultMQProducer producer = new DefaultMQProducer("group1");
//			//2.设定发送的命名服务器地址
//			producer.setNamesrvAddr("192.168.111.132:9876");
//			//3.启动发送的服务
//			producer.start();
//			//4.创建要发送的消息对象,指定topic，指定内容body
//			Message msg = new Message("topic1","hello rocketmq".getBytes("UTF-8"));
//			//5.发送消息
//			SendResult result = producer.send(msg);
//			System.out.println("返回结果："+result);
//			//6.关闭连接
//			producer.shutdown();
//		}
//	}
//
////3.RocketMQ-消费者
//	public class Consumer {
//		public static void main(String[] args) throws Exception {
//			//1.创建一个接收消息的对象Consumer
//			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
//			//2.设定接收的命名服务器地址
//			consumer.setNamesrvAddr("192.168.111.132:9876");
//			//3.设置接收消息对应的topic,对应的sub标签为任意*
//			consumer.subscribe("topic1", "*");
//			//4.开启监听，用于接收消息
//			consumer.registerMessageListener(new MessageListenerConcurrently() {
//				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//					//遍历消息
//					for (MessageExt msg : list) {
//
//						System.out.println("消息：" + new String(msg.getBody()));
//					}
//					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//				}
//			});
//			//5.启动接收消息的服务
//			consumer.start();
//			System.out.println("接收消息服务已开启运行");
//		}
//	}
//
////
////4.消费者-消费模式
////	概述: 消费者在消费消息的时候,默认使用负载均衡模式.
////	语法:
//		consumer.setMessageModel(MessageModel.BROADCASTING);  //设置为"广播模式(接收所有消息)"
//
////5.生产者-消息类别
////	概述: 生产者发送消息的时候,是否等待"命名服务器"的回执.和"消费者"无关.
////	分类:
////		同步消息:必须要等到"命名服务器"接收消息后的回执,才继续往下执行
////		异步消息:不需要一直等待"命名服务器"接收消息后的回执.
////		单向消息:只管发送消息,不接收回执.
////	语法:
//		//同步消息
//		SendResult result = producer.send(msg);
//		//异步消息
//		producer.send(msg, new SendCallback(){ ... })
//		//单项消息
//		producer.sendOneway(msg);
//
////6.消息过滤-tag过滤
//	//生产者-生产消息时指定tag标签
//	Message msg = new Message("topic","tag标签","hello rocketmq".getBytes("UTF-8"));
//	//消费者-接收消息时指定tag标签
//	consumer.subscribe("topic","tag标签");	//多个标签中间使用"||"隔开,*代表任意标签
//
////7.顺序消息
////	概述:要求消费者在消费消息时,必须保证消费的顺序.
////	思路:
////		1:生产者在发送消息时,保证消息的顺序,并且保证这些消息放在同一个队列里边.
////		2:消费者在消费消息的时候,必须保证同一个队列中,第一个消息消费完成后,才能消费第二个消息
////	代码:
//		//生产者-保证消息进入同一队列
//            Message msg = new Message("orderTopic",order.toString().getBytes());
//            //发送时要指定对应的消息队列选择器
//            SendResult result = producer.send(msg, new MessageQueueSelector() {
//                //设置当前消息发送时使用哪一个消息队列
//                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
//                    //根据发送的信息不同，选择不同的消息队列
//                    //根据id来选择一个消息队列的对象，并返回->id得到int值
//                    int mqIndex = order.getId().hashCode() % list.size();
//                    return list.get(mqIndex);
//                }
//            }, null);
//		//消费者-保证消息的消费顺序
//			consumer.registerMessageListener(new MessageListenerOrderly(){...});
//
////8.事务消息
////	概述:在发送消息时,需要根据方法的返回值来确定,"消息是否发送出去"
////	代码:
//		//1.创建事务消息发送对象TransactionMQProducer
//        TransactionMQProducer producer = new TransactionMQProducer("group1");
//		//2.指定命名服务器的位置
//        producer.setNamesrvAddr("192.168.111.132:9876");
//        //3.添加本地事务对应的监听
//        producer.setTransactionListener(new TransactionListener() {
//            //正常事务过程
//            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
//                return LocalTransactionState.ROLLBACK_MESSAGE;
//            }
//            //事务补偿过程
//            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
//                return null;
//            }
//        });
//        producer.start();
//
//        Message msg = new Message("topic9",("事务消息：hello rocketmq ").getBytes("UTF-8"));
//        SendResult result = producer.sendMessageInTransaction(msg,null);
//        System.out.println("返回结果："+result);
//        producer.shutdown();
////	流程:
////		当生产者准备发送消息时:
////		1:先执行TransactionListener监听器中的executeLocalTransaction方法.
////			如果方法返回值是"COMMIT_MESSAGE",则发送消息.
////			如果方法返回值是"ROLLBACK_MESSAGE",则不发送消息(消息作废).
////			如果方法返回值是"UNKNOW",则等待一会之后,执行事务补偿机制,也就是执行checkLocalTransaction方法.
////		2:执行事务补偿机制的checkLocalTransaction方法的时候
////			如果方法返回值是"COMMIT_MESSAGE",则发送消息.
////			如果方法返回值是"ROLLBACK_MESSAGE",则不发送消息(消息作废).
////			如果方法返回值是"UNKNOW",则再次等待,之后,继续补偿机制.
//
//