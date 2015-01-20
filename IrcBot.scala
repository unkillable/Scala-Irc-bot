import java.net._
import java.io._
import scala.io._

object IrcBot {
  def main(args : Array[String]) {
	var nick: String = "NICK ScalaBot\r\n";
	var user: String = "USER ScalaBot ScalaBot ScalaBot :ScalaBot\r\n";
	var join: String = "JOIN #mootsinsuits\r\n";
    	val socket = new Socket(InetAddress.getByName("irc.tm"), 6667)
	val in = new BufferedSource(socket.getInputStream).getLines
	val out = new PrintStream(socket.getOutputStream)
	while(true){
		var data: String = in.next;
		println(data);
		if(data contains "001"){
			out.println(join);
			out.flush();
		}
		if(data contains "Found your hostname"){
			out.println(user);
			out.flush;
			out.println(nick);
			out.flush();	
		}
		if(data contains "PING :"){
			if(data contains "You have not registered"){
			}
			else{
				var hash_array: Array[String] = data.split("PING :");
				println("Sending " + "PONG :"+hash_array(1));
				out.println("PONG :"+hash_array(1)+"\r\n");
				out.flush();
			}
		 }
		if(data contains ".quit"){
			out.println("QUIT I'm gay\r\n");
			out.flush();
			socket.close;
		 }
		if(data contains ".scala"){
			out.println("PRIVMSG #mootsinsuits :I am a bot made in scala\r\n");
			out.flush();
		}
	  }
   }  
}
