package e12

/* Features:
 * - tail recursion
 * - default parameter values
 * - pattern matching
 */
object TailRecursion:
  def contains(list: List[String], value: String): Boolean =
    list match
      case `value` :: _ => true
      case _ :: rest => contains(rest, value)
      case Nil => false

  def length(list: List[String]): Int =
    list match
      case _ :: rest => 1 + length(rest)
      case Nil => 0
  
  def length2(list: List[String], acc: Int = 0): Int =
    list match
      case _ :: rest => length2(rest, acc + 1)
      case Nil => acc


  def main(args: Array[String]): Unit =
    val list = List("aa", "bb", "cc", "dd")
    val list2 = "aa" :: "bb" :: "cc" :: "dd" :: Nil
    println(contains(list, "bb"))

    /* ASSIGNMENT:
     * Write a method length2 that has the same semantics of length but can be optimized using tail recursion.
     */
    println(length(list))
    println(length2(list2))