import org.scalatra._
import com.novus.casbah.mongodb.Imports._
import scala.xml._
import com.mongodb._

class SimpleWebapp extends ScalatraServlet {

  val mongo = MongoConnection()
  val coll = mongo("msgs")("mongosv")
  
  get("/") {
    <h1>Hello MongoSV!</h1>
  }

  get("/msgs") {
     <body>
       <ul>
         {for (l <- coll) yield <li> 
	 ({l.getOrElse("vote", 0)})<a href={"/msgs/upvote/"+l("_id")}>+</a>/
	 <a href={"/msgs/downvote/"+l("_id")}>-</a> 
	 From: <a href={"/msgs/"+l.getOrElse("author", "???")}>{l.getOrElse("author", "???")}</a> - 
	 {l("msg")}</li>}
       </ul>
       <form method="POST" action="/msgs">
         Author: <input type="text" name="author"/><br/>
         Message: <input type="text" name="msg"/><br/>
	 <input type="submit"/>
       </form>
     </body>
   
 }
 
 post("/msgs") { 
   val builder = MongoDBObject.newBuilder
   builder += "author" -> params("author")
   builder += "msg" -> params("msg")

   coll += builder.result.asDBObject
   redirect("/msgs")
 }
 
 get("/msgs/:author") {
   val builder = MongoDBObject.newBuilder
   builder += "author" -> params("author")
   val res = coll.find(builder.result.asDBObject)
   
   <html>
     <h1>Messages from {params("author")}</h1>
     <ul>
       {for (m <- res) yield <li>{m("msg")}</li>} 
     </ul>
   </html>
 }

 get("/msgs/upvote/:id") {
   val inc = $inc("vote" -> 1)
   coll.update(MongoDBObject("_id" -> new ObjectId(params("id"))), inc)
   redirect("/msgs")
 }
 
  get("/msgs/downvote/:id") {
   val inc = $inc("vote" -> -1)
   coll.update(MongoDBObject("_id" -> new ObjectId(params("id"))), inc)
   redirect("/msgs")
 }
 
}
