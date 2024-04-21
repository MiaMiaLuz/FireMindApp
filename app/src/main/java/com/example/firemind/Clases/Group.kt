
import java.util.Date
import java.security.Permissions
import User

public class Group {

    var groupId : Int = 0
    lateinit var groupName : String
    lateinit var users : Map <Int , User>
    lateinit var groupPermissions : Permissions
    
    fun showGroup(){
        var it = users.iterator()
        while (it.hasNext()){
            print(it.next().toString())
        }
    }
}