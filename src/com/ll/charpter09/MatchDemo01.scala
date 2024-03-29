package com.ll.charpter09

object MatchDemo01 {
  def main(args: Array[String]): Unit = {
    val oper = '#'
    val n1 = 20
    val n2 = 10
    var res = 0
    //说明
    //1. match(类似java switch) 和 case 是关键字
    //2. 如果匹配成功，则执行 => 后面的代码块
    //3. 匹配的顺序是从上到下，匹配到一个就执行对应的代码
    //4. => 后面的代码块不要写break，会自动的退出match
    //5. 如果一个都没有匹配到，则执行case _ 后面的代码
    oper match {
      case '+' => res = n1 + n2
      case '-' => res = n1 - n2
      case '*' => n1 * n2
      case '/' => n1 / n2
      case _ => println("oper error")   //  oper error
     }
    println("res = " + res)   //  res = 0
  }
}
