
import java.security.Permissions

public class Group {

    var groupId : Int = 0
    lateinit var groupName : String
    lateinit var users : Map <Int , UserCollection>
    lateinit var groupPermissions : Permissions
    
    fun showGroup(){
        var it = users.iterator()
        while (it.hasNext()){
            print(it.next().toString())
        }
    }
}