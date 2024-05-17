
import com.example.firemind.Clases.Listados
import com.example.firemind.DatabaseManager.DatabaseManager
import java.security.Permissions

public class Group {
    var databaseManager = DatabaseManager()
    var groupId : String = ""
    var groupName : String
    var icon : String
    var users : Map <Int , User>
    var groupPermissions : Permissions
    var tasks : Map<User,ArrayList<Task>>
    var list : ArrayList<Listados>

    constructor(
        groupName: String,
        icon: String,
        users: Map<Int, User>,
        groupPermissions: Permissions,
        tasks:  Map<User,ArrayList<Task>>,
        list: ArrayList<Listados>,
    ) {
        this.groupName = groupName
        this.icon = icon
        this.users = users
        this.groupPermissions = groupPermissions
        this.tasks = tasks
        this.list = list
    }
    fun showGroup(){
        var it = users.iterator()
        while (it.hasNext()){
            print(it.next().toString())
        }
    }

    suspend fun getUsers(){
        databaseManager.getUsersFromGroup(groupId)
    }

}