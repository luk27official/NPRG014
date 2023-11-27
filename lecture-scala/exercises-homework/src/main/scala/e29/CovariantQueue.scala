package e29

/* Features:
 * - covariance with internal state
 */

object CovariantQueue:
	def apply() = new CovariantQueue[Nothing](Nil, Nil)

class CovariantQueue[+T] private (
		private var leading: List[T],
		private var trailing: List[T]
	):

	private def mirror(): Unit =
		// The compiler checks that all accesses to the mutable state are within a single instance only
		if leading.isEmpty then
			leading = trailing.reverse
			trailing = Nil

	def head: T =
		mirror()
		leading.head
	
	def tail: CovariantQueue[T] =
		mirror()
		new CovariantQueue(leading.tail, trailing)
	
	def enqueue[U >: T](x: U) = new CovariantQueue[U](leading, x :: trailing)

object Main:
	def main(args: Array[String]): Unit =
		val q = CovariantQueue.apply()
		val q1 = q.enqueue(1)
		val q2 = q1.enqueue(2)
		val q3 = q2.enqueue(3)
		println(q3.head)
		println(q3.tail.head)
		println(q3.tail.tail.head)
		// println(q3.tail.tail.tail.head) // error: no such method
		// println(q3.tail.tail.tail.tail.head) // error: no such method

