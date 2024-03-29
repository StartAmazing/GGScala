package com.ll.charpter05.selftype

object SelfTypeDemo {
  def main(args: Array[String]): Unit = {

  }
}

//Logger就是自身类型特质，当这里做了自身类型后，那么
//trait Logger extends Exception,要求混入该特质的类也必须是Exception的子类
trait Logger{
  //明确告诉编译器，我就是Exception,如果没有这句话的话，下面的getMessage将不可用
  this: Exception =>
  def log(): Unit = {
    //既然我就是Exception,那么就可以调用其中的方法
    println(getMessage)
  }
}

//class Console extends logger{}    //error
class Console extends Exception with Logger{} //ok
