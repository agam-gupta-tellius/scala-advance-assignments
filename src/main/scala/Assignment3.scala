object Assignment3 extends App {

  /* All type of Json Values */
  trait JsonValue {
    def stringify: String
  }
  object JsonValue {
    case class JsonString(str: String) extends JsonValue {
      override def stringify: String = s"\"$str\""
    }

    case class JsonInteger(num: Int) extends JsonValue {
      override def stringify: String = num.toString
    }

    case class JsonList(list: List[JsonValue]) extends JsonValue {
      override def stringify: String = list.map(_.stringify).mkString("[", ", ", "]")
    }

    case class JsonObject(obj: Map[String, JsonValue]) extends JsonValue {
      override def stringify: String =
        obj.map((key, value) => s"$key: ${value.stringify}").mkString("{", ", ", "}")
    }
  }


  case class User(name: String, emails: List[String], age: Int)
  /* JSON serializer */
  trait JsonSerializer[T] {
    def serialize(value: T): JsonValue
  }

  object JsonSerializer {
    import JsonValue._
    def apply[T](value: T)(implicit serializer: JsonSerializer[T]): JsonValue = serializer.serialize(value)

    implicit object UserSerializer extends JsonSerializer[User] {
      override def serialize(value: User): JsonValue = JsonObject(Map(
        "name" -> JsonString(value.name),
        "emails" -> JsonList(value.emails.map(JsonString)),
        "age" -> JsonInteger(value.age)
      ))
    }
  }

  val user1 = User("user1", List("user1@gmail.com"), 22)
  val jsonString = JsonSerializer[User](user1).stringify
  println(jsonString)
}
