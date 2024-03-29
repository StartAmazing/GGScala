package com.ll.charpter04.mixin

/**
  * @package com.ll.charpter04.mixin
  * @Author LL
  * @ClassName AddTraits
  * @Date 2019/8/23  10:16
  */
//看着混入多个特质的特点（叠加特质）
object AddTraits {
  def main(args: Array[String]): Unit = {
    //说明
    //1. 创建MySQL实例时，动态混入了DB4和File4
    //研究第一个问题，当我们创建一个动态混入的对象时，其顺序是怎样的
    //1. Operate4
    //2. Data4
    //3. DB4
    //4. File4
    //总结一句话，在叠加特质时，会从左到右的特质开始执行
    val mysql = new MySQL4 with DB4 with File4
    println(mysql)

    //研究第二个问题，当我们执行一个动态混入对象的方法时，其执行顺序是怎样的
    //顺序是，(1)从右到左开始执行(类似栈)，(2)当执行到super时，指的是左边的特质,(3)如果左边没有特质的话，则super就是父特质
    //1. 向文件
    //2. 向数据库
    //3. 插入数据 100
    mysql.insert(100)

    println("==================")

    //练习题
    val mysql4 = new MySQL4 with File4 with DB4
    mysql4.insert(666)
    //构建顺序
    //1.Operate4..
    //2. Data4
    //3. File4
    //4. DB4

    //执行顺序
    // 1. 向数据库
    // 2. 向文件
    // 3. 插入数据 id = 666
  }
}

trait Operate4{ //特点
  println("Operate4..")
  def insert(id: Int) //抽象方法
}

trait Data4 extends Operate4{   //特质继承了Operate4
  println("Data4")

  override def insert(id: Int): Unit = {    //实现/重写了Operate4中的insert方法
    println("插入数据： " + id)
  }
}

trait DB4 extends Data4{  //特质，继承Data4
  println("DB4")

  override def insert(id: Int): Unit = {  //重写了Data4中的insert方法
    println("向数据库")
    super.insert(id)
  }
}

trait File4 extends Data4{
  println("File4")

  override def insert(id: Int): Unit = {
    println("向文件")
//    super.insert(id)    //调用了insert方法（这里的super在动态混入时不一定是父类）
    //如果我们希望直接调用Data的insert方法，可以指定，如下
    //说明：super[?] ？的类型 必须是当前特质的直接父特质（超类）
    super[Data4].insert(id)
  }
}
class MySQL4{   //普通类

}